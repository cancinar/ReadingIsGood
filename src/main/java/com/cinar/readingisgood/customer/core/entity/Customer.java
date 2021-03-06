package com.cinar.readingisgood.customer.core.entity;

import com.cinar.readingisgood.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Customer extends BaseEntity {

  @Id
  private String id;

  private String name;

  private String surname;

  @Indexed(name = "customer_email_uk", unique = true)
  private String email;

  private String password;
}
