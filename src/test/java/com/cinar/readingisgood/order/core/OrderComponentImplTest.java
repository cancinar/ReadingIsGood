package com.cinar.readingisgood.order.core;

import static com.cinar.readingisgood.generators.RandomGenerator.randomBookName;
import static com.cinar.readingisgood.generators.RandomGenerator.randomDouble;
import static com.cinar.readingisgood.generators.RandomGenerator.randomEmail;
import static com.cinar.readingisgood.generators.RandomGenerator.randomList;
import static com.cinar.readingisgood.generators.domain.BookDomainGenerator.randomBookDomain;
import static com.cinar.readingisgood.generators.domain.CustomerDomainGenerator.randomCustomerDomain;
import static com.cinar.readingisgood.order.core.domain.enums.OrderStatus.COMPLETED;
import static com.cinar.readingisgood.order.core.domain.enums.OrderStatus.REJECTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.cinar.readingisgood.base.exception.ValidationException;
import com.cinar.readingisgood.book.core.BookComponent;
import com.cinar.readingisgood.book.core.domain.BookDomain;
import com.cinar.readingisgood.customer.core.CustomerComponent;
import com.cinar.readingisgood.customer.core.domain.CustomerDomain;
import com.cinar.readingisgood.generators.entity.OrderGenerator;
import com.cinar.readingisgood.order.core.domain.OrderDomain;
import com.cinar.readingisgood.order.core.entity.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class OrderComponentImplTest {

  private OrderComponent orderComponent;

  @Mock
  private OrderJPARepository orderJPARepository;

  @Mock
  private BookComponent bookComponent;

  @Mock
  private CustomerComponent customerComponent;

  @BeforeEach
  public void init() {
    orderComponent = new OrderComponentImpl(orderJPARepository, bookComponent, customerComponent);
  }

  @Test
  public void createOrder_whenCountIsZero_thenThrowValidationException() {
    String bookName = randomBookName();
    Long count = 0L;
    String customerName = randomEmail();

    Exception exception = assertThrows(ValidationException.class,
        () -> orderComponent.createOrder(bookName, count, customerName));

    assertEquals("Count should be more than zero.", exception.getMessage());

  }

  @Test
  public void createOrder_given_bookDomainAndCustomerDomain_whenBookStockInsufficient_thenReject() {
    BookDomain bookDomain = randomBookDomain();
    bookDomain.setStock(0L);

    CustomerDomain customerDomain = randomCustomerDomain();
    Long count = 2L;

    when(bookComponent.getBook(bookDomain.getName())).thenReturn(bookDomain);
    when(customerComponent.getCustomer(customerDomain.getEmail())).thenReturn(customerDomain);
    when(orderJPARepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

    final OrderDomain order = orderComponent
        .createOrder(bookDomain.getName(), count, customerDomain.getEmail());

    assertEquals(REJECTED, order.getStatus());
  }

  @Test
  public void createOrder_given_bookDomainAndCustomerDomain_whenBookStockSufficient_thenCompleted() {
    BookDomain bookDomain = randomBookDomain();
    bookDomain.setStock(3L);

    CustomerDomain customerDomain = randomCustomerDomain();
    Long count = 2L;

    when(bookComponent.getBook(bookDomain.getName())).thenReturn(bookDomain);
    when(customerComponent.getCustomer(customerDomain.getEmail())).thenReturn(customerDomain);
    when(orderJPARepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

    final OrderDomain order = orderComponent
        .createOrder(bookDomain.getName(), count, customerDomain.getEmail());

    assertEquals(COMPLETED, order.getStatus());
  }

  @Test
  public void findAllByDate_givenOrder_thenReturn() {
    List<Order> orders = randomList(OrderGenerator::randomOrder);
    LocalDate now = LocalDate.now();

    when(orderJPARepository.findByCreateDateBetween(now, now)).thenReturn(orders);

    final List<OrderDomain> orderDomains = orderComponent.findAllByDate(now, now);

    assertEquals(orders
            .stream()
            .map(OrderDomain::fromOrder)
            .collect(Collectors.toList()),
        orderDomains);
  }

  @Test
  public void findAll_givenOrder_thenReturn() {
    List<Order> orders = randomList(OrderGenerator::randomOrder);

    when(orderJPARepository.findAll()).thenReturn(orders);

    final List<OrderDomain> orderDomains = orderComponent.findAll();

    assertEquals(orders
            .stream()
            .map(OrderDomain::fromOrder)
            .collect(Collectors.toList()),
        orderDomains);
  }

  @Test
  public void findAllInPage_givenOrder_thenReturn() {
    List<Order> orders = randomList(OrderGenerator::randomOrder, 10);

    String customerEmail = randomEmail();
    final Pageable pageable = PageRequest.of(1, 5);
    final int start = (int) pageable.getOffset();
    final int end = Math.min((start + pageable.getPageSize()), orders.size());
    final Page<Order> ordersPage = new PageImpl<>(orders.subList(start, end), pageable,
        orders.size());

    when(orderJPARepository.findOrderByCustomerEmailIgnoreCase(customerEmail, pageable))
        .thenReturn(ordersPage);

    final List<OrderDomain> orderDomains = orderComponent
        .findByCustomerName(customerEmail, pageable.getPageNumber());

    assertEquals(pageable.getPageSize(), orderDomains.size());
  }

  @Test
  public void getTotalPurchasedAmount_thenReturn() {
    final Double amount = randomDouble();

    when(orderJPARepository.sumTotalPurchasedAmount()).thenReturn(amount);

    assertEquals(amount, orderComponent.getTotalPurchasedAmount());
  }
}
