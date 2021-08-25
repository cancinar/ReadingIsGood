package com.cinar.readingisgood.customer.core;

import com.cinar.readingisgood.customer.core.domain.CustomerDomain;

public interface CustomerComponent {

  CustomerDomain addCustomer(String name, String surname, String mail, String password);

  CustomerDomain getCustomer(String mail);
}
