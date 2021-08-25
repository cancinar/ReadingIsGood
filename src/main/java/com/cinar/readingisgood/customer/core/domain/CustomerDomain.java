package com.cinar.readingisgood.customer.core.domain;

import com.cinar.readingisgood.customer.core.entity.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDomain {

  private String id;
  private String name;
  private String surname;
  private String email;
  private String password;

  public static CustomerDomain fromCustomer(Customer customer) {
    if (customer != null) {
      return CustomerDomain.builder()
          .id(customer.getId())
          .name(customer.getName())
          .surname(customer.getSurname())
          .email(customer.getEmail())
          .build();
    }
    return null;
  }

  public static CustomerDomain fromSecureCustomer(Customer customer) {
    if (customer != null) {
      final CustomerDomain customerDomain = fromCustomer(customer);
      customerDomain.setPassword(customer.getPassword());
      return customerDomain;
    }
    return null;
  }
}
