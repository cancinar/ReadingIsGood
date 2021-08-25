package com.cinar.readingisgood.book.core.entity;

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
public class Book extends BaseEntity {

  @Id
  private String id;

  @Indexed(name = "book_name_uk", unique = true)
  private String name;

  private Long stock;

  private Double price;
}
