package com.cinar.readingisgood.statistics.core;

import com.cinar.readingisgood.order.core.domain.OrderDomain;
import java.util.List;
import java.util.Map;

public interface StatisticsComponent {

  Long getTotalOrderCount();

  Double getTotalAmountOfOrder();

  Map<String, List<OrderDomain>> getOrderDetailPerMonth();

}
