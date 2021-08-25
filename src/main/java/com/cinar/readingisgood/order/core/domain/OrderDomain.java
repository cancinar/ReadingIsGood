package com.cinar.readingisgood.order.core.domain;

import com.cinar.readingisgood.order.core.domain.enums.OrderStatus;
import com.cinar.readingisgood.order.core.entity.Order;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDomain {

  private String id;
  private Double amount;
  private OrderStatus status;
  private String owner;
  private LocalDate createdDate;

  public static OrderDomain fromOrder(Order order) {
    if (order != null) {
      return OrderDomain.builder()
          .status(order.getStatus())
          .amount(order.getAmount())
          .id(order.getId())
          .owner(order.getCustomerEmail())
          .createdDate(order.getCreateDate())
          .build();
    }
    return null;
  }
}
