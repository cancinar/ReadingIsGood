package com.cinar.readingisgood.order.core;

import com.cinar.readingisgood.order.core.entity.Order;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJPARepository extends MongoRepository<Order, Long> {

  List<Order> findByCreateDateBetween(LocalDate startDate, LocalDate endDate);

  Page<Order> findOrderByCustomerEmailIgnoreCase(String customerEmail, Pageable page);

  @Aggregation(pipeline = {
      "{$group: { _id: '', total: {$sum: $amount}}}"
  })
  Double sumTotalPurchasedAmount();
}
