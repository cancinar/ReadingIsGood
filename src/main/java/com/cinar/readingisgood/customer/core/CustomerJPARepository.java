package com.cinar.readingisgood.customer.core;

import com.cinar.readingisgood.customer.core.entity.Customer;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPARepository extends MongoRepository<Customer, Long> {

  Optional<Customer> findByEmailIgnoreCase(String email);
}
