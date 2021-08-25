package com.cinar.readingisgood.customer.view.dto;

import com.cinar.readingisgood.customer.core.domain.CustomerDomain;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

  private String id;
  private String name;

  public static CustomerDto fromCustomerDomain(CustomerDomain customerDomain) {
    return CustomerDto.builder()
        .id(customerDomain.getId())
        .name(customerDomain.getName())
        .build();
  }
}
