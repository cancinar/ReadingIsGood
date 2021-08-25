package com.cinar.readingisgood.generators.entity;

import static com.cinar.readingisgood.generators.RandomGenerator.randomDouble;
import static com.cinar.readingisgood.generators.RandomGenerator.randomEmail;
import static com.cinar.readingisgood.generators.RandomGenerator.randomEnum;
import static com.cinar.readingisgood.generators.RandomGenerator.randomId;

import com.cinar.readingisgood.order.core.domain.enums.OrderStatus;
import com.cinar.readingisgood.order.core.entity.Order;

public class OrderGenerator {

  public static Order randomOrder() {
    return Order.builder()
        .id(randomId())
        .amount(randomDouble())
        .customerEmail(randomEmail())
        .status(randomEnum(OrderStatus.class))
        .build();
  }
}
