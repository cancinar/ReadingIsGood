package com.cinar.readingisgood.statistics.core;

import com.cinar.readingisgood.order.core.OrderComponent;
import com.cinar.readingisgood.order.core.domain.OrderDomain;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticsComponentImpl implements StatisticsComponent {

  private final OrderComponent orderComponent;

  @Override
  public Long getTotalOrderCount() {
    return orderComponent.count();
  }

  @Override
  public Double getTotalAmountOfOrder() {
    return orderComponent.getTotalPurchasedAmount();
  }

  public Map<String, List<OrderDomain>> getOrderDetailPerMonth() {
    return orderComponent.findAll()
        .stream()
        .collect(Collectors.groupingBy(item -> item.getCreatedDate().getMonth().toString()));
  }

}
