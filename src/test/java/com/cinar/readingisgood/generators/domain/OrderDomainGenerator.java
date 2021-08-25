package com.cinar.readingisgood.generators.domain;

import static com.cinar.readingisgood.generators.RandomGenerator.randomDouble;
import static com.cinar.readingisgood.generators.RandomGenerator.randomEmail;
import static com.cinar.readingisgood.generators.RandomGenerator.randomEnum;
import static com.cinar.readingisgood.generators.RandomGenerator.randomId;

import com.cinar.readingisgood.order.core.domain.OrderDomain;
import com.cinar.readingisgood.order.core.domain.enums.OrderStatus;
import java.time.LocalDate;

public class OrderDomainGenerator {

  public static OrderDomain randomOrderDomain() {
    return OrderDomain.builder()
        .amount(randomDouble())
        .owner(randomEmail())
        .status(randomEnum(OrderStatus.class))
        .id(randomId())
        .createdDate(LocalDate.now())
        .build();
  }
}
