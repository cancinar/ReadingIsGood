package com.cinar.readingisgood.order.core;

import com.cinar.readingisgood.order.core.domain.OrderDomain;
import java.time.LocalDate;
import java.util.List;

public interface OrderComponent {

  OrderDomain createOrder(String bookName, Long count, String customerName);

  List<OrderDomain> findAllByDate(LocalDate startDate, LocalDate endDate);

  List<OrderDomain> findAll();

  List<OrderDomain> findByCustomerName(String customerName, int page);

  long count();

  Double getTotalPurchasedAmount();
}
