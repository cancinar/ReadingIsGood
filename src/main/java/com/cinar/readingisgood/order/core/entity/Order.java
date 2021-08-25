package com.cinar.readingisgood.order.core.entity;

import com.cinar.readingisgood.base.entity.BaseEntity;
import com.cinar.readingisgood.order.core.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class Order extends BaseEntity {

  @Id
  private String id;

  private String customerEmail;

  private Double amount;

  private Long count;

  private OrderStatus status;
}
