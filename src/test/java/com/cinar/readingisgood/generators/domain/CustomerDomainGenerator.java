package com.cinar.readingisgood.generators.domain;

import static com.cinar.readingisgood.generators.RandomGenerator.randomEmail;
import static com.cinar.readingisgood.generators.RandomGenerator.randomName;
import static com.cinar.readingisgood.generators.RandomGenerator.randomPassword;
import static com.cinar.readingisgood.generators.RandomGenerator.randomSurname;

import com.cinar.readingisgood.customer.core.domain.CustomerDomain;

public class CustomerDomainGenerator {

  public static CustomerDomain randomCustomerDomain() {
    return CustomerDomain.builder()
        .email(randomEmail())
        .name(randomName())
        .surname(randomSurname())
        .password(randomPassword())
        .build();
  }
}
