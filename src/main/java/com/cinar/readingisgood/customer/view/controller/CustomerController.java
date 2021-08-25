package com.cinar.readingisgood.customer.view.controller;

import com.cinar.readingisgood.customer.core.CustomerComponent;
import com.cinar.readingisgood.customer.core.domain.CustomerDomain;
import com.cinar.readingisgood.customer.view.dto.AddCustomerDto;
import com.cinar.readingisgood.customer.view.dto.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerComponent customerComponent;

  @PostMapping
  public ResponseEntity<CustomerDto> addCustomer(
      @RequestBody AddCustomerDto addCustomerDto) {
    final CustomerDomain customerDomain = customerComponent
        .addCustomer(addCustomerDto.getName(), addCustomerDto.getSurname(),
            addCustomerDto.getEmail(), addCustomerDto.getPassword());

    return ResponseEntity.ok(CustomerDto.fromCustomerDomain(customerDomain));
  }

}
