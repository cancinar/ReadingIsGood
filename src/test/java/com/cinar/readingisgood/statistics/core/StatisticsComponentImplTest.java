package com.cinar.readingisgood.statistics.core;

import static com.cinar.readingisgood.generators.RandomGenerator.randomDouble;
import static com.cinar.readingisgood.generators.RandomGenerator.randomList;
import static com.cinar.readingisgood.generators.RandomGenerator.randomLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.cinar.readingisgood.generators.domain.OrderDomainGenerator;
import com.cinar.readingisgood.order.core.OrderComponent;
import com.cinar.readingisgood.order.core.domain.OrderDomain;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatisticsComponentImplTest {

  private StatisticsComponent statisticsComponent;

  @Mock
  private OrderComponent orderComponent;

  @BeforeEach
  public void init() {
    statisticsComponent = new StatisticsComponentImpl(orderComponent);
  }

  @Test
  public void getTotalOrderCount_thenReturn() {
    Long count = randomLong();

    when(orderComponent.count()).thenReturn(count);

    final Long totalOrderCount = statisticsComponent.getTotalOrderCount();

    assertEquals(count, totalOrderCount);
  }

  @Test
  public void getTotalAmountOfOrder_thenReturn() {
    Double amount = randomDouble();

    when(orderComponent.getTotalPurchasedAmount()).thenReturn(amount);

    final Double totalOrderCount = statisticsComponent.getTotalAmountOfOrder();

    assertEquals(amount, totalOrderCount);
  }

  @Test
  public void getOrderDetailPerMonth_thenReturn() {
    List<OrderDomain> orders = randomList(OrderDomainGenerator::randomOrderDomain);

    when(orderComponent.findAll()).thenReturn(orders);

    final Map<String, List<OrderDomain>> orderDetailPerMonth = statisticsComponent
        .getOrderDetailPerMonth();

    assertEquals(orders, orderDetailPerMonth.values().stream().flatMap(Collection::stream).collect(
        Collectors.toList()));
  }
}
