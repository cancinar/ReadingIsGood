package com.cinar.readingisgood.order.core;


import com.cinar.readingisgood.base.exception.ValidationException;
import com.cinar.readingisgood.book.core.BookComponent;
import com.cinar.readingisgood.book.core.domain.BookDomain;
import com.cinar.readingisgood.customer.core.CustomerComponent;
import com.cinar.readingisgood.customer.core.domain.CustomerDomain;
import com.cinar.readingisgood.order.core.domain.OrderDomain;
import com.cinar.readingisgood.order.core.domain.enums.OrderStatus;
import com.cinar.readingisgood.order.core.entity.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
class OrderComponentImpl implements OrderComponent {

  private final OrderJPARepository orderJPARepository;
  private final BookComponent bookComponent;
  private final CustomerComponent customerComponent;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public synchronized OrderDomain createOrder(String bookName, Long count, String customerMail) {
    BookDomain book = bookComponent.getBook(bookName);
    CustomerDomain customerDomain = customerComponent.getCustomer(customerMail);
    if (count <= 0) {
      throw new ValidationException("Count should be more than zero.");
    }

    if (book.getStock() >= count) {
      Long newStock = book.getStock() - count;
      Double totalAmount = count * book.getPrice();
      bookComponent.updateStock(bookName, newStock);
      return OrderDomain.fromOrder(orderJPARepository.save(Order.builder()
          .count(count)
          .status(OrderStatus.COMPLETED)
          .customerEmail(customerDomain.getEmail())
          .amount(totalAmount)
          .build()));
    }

    return OrderDomain.fromOrder(orderJPARepository.save(Order.builder()
        .count(count)
        .status(OrderStatus.REJECTED)
        .customerEmail(customerDomain.getEmail())
        .build()));
  }

  @Override
  public List<OrderDomain> findAllByDate(LocalDate startDate, LocalDate endDate) {
    return orderJPARepository.findByCreateDateBetween(startDate, endDate)
        .stream()
        .map(OrderDomain::fromOrder)
        .collect(Collectors.toList());
  }

  @Override
  public List<OrderDomain> findAll() {
    return orderJPARepository.findAll()
        .stream()
        .map(OrderDomain::fromOrder)
        .collect(Collectors.toList());
  }

  @Override
  public List<OrderDomain> findByCustomerName(String customerEmail, int page) {
    return orderJPARepository.findOrderByCustomerEmailIgnoreCase(customerEmail, PageRequest.of(page, 5))
        .stream()
        .map(OrderDomain::fromOrder)
        .collect(Collectors.toList());  }

  @Override
  public long count() {
    return orderJPARepository.count();
  }

  @Override
  public Double getTotalPurchasedAmount() {
    return orderJPARepository.sumTotalPurchasedAmount();
  }
}