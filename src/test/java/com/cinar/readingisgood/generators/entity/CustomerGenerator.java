package com.cinar.readingisgood.generators.entity;

import static com.cinar.readingisgood.generators.RandomGenerator.randomEmail;
import static com.cinar.readingisgood.generators.RandomGenerator.randomId;
import static com.cinar.readingisgood.generators.RandomGenerator.randomName;
import static com.cinar.readingisgood.generators.RandomGenerator.randomPassword;
import static com.cinar.readingisgood.generators.RandomGenerator.randomSurname;

import com.cinar.readingisgood.customer.core.entity.Customer;

public class CustomerGenerator {

  public static Customer randomCustomer() {
    return Customer.builder()
        .id(randomId())
        .name(randomName())
        .surname(randomSurname())
        .password(randomPassword())
        .email(randomEmail())
        .build();
  }
}
