package com.cinar.readingisgood.customer.view.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCustomerDto {

  private String name;
  private String surname;
  private String email;
  private String password;
}
