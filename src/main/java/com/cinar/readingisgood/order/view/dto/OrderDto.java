package com.cinar.readingisgood.order.view.dto;

import com.cinar.readingisgood.order.core.domain.OrderDomain;
import com.cinar.readingisgood.order.core.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

  private String id;
  private OrderStatus status;

  public static OrderDto fromOrderDomain(OrderDomain orderDomain) {
    return OrderDto.builder()
        .id(orderDomain.getId())
        .status(orderDomain.getStatus())
        .build();
  }
}
