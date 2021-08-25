package com.cinar.readingisgood.customer.core;


import static org.apache.logging.log4j.util.Strings.isEmpty;

import com.cinar.readingisgood.base.exception.ValidationException;
import com.cinar.readingisgood.customer.core.domain.CustomerDomain;
import com.cinar.readingisgood.customer.core.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class CustomerComponentImpl implements CustomerComponent {

  private final CustomerJPARepository customerJPARepository;

  @Override
  public CustomerDomain addCustomer(String name, String surname, String email, String password) {
    if (isEmpty(name) || isEmpty(surname) || isEmpty(email) || isEmpty(password)) {
      throw new ValidationException("Name, Surname, Password or Email cannot be empty.");
    }

    customerJPARepository.findByEmailIgnoreCase(email)
        .ifPresent(customer -> {
          throw new ValidationException(customer.getEmail() + " has already been added.");
        });

    return CustomerDomain
        .fromCustomer(customerJPARepository.save(Customer.builder()
            .name(name)
            .surname(surname)
            .email(email)
            .password(new BCryptPasswordEncoder().encode(password))
            .build()));
  }

  @Override
  public CustomerDomain getCustomer(String name) {
    return customerJPARepository.findByEmailIgnoreCase(name)
        .map(CustomerDomain::fromSecureCustomer)
        .orElseThrow(() -> new ValidationException("Customer cannot be found!"));
  }
}
