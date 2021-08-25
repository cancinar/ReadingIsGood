package com.cinar.readingisgood.auth.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.cinar.readingisgood.customer.core.CustomerComponent;
import com.cinar.readingisgood.customer.core.domain.CustomerDomain;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final CustomerComponent customerComponent;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final CustomerDomain customer = customerComponent.getCustomer(email);
    if (!isEmpty(customer)) {
      return new User(customer.getEmail(), customer.getPassword(),
          new ArrayList<>());
    } else {
      throw new UsernameNotFoundException("User not found with username: " + email);
    }
  }
}