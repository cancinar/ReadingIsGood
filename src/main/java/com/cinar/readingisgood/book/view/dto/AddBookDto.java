package com.cinar.readingisgood.book.view.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class AddBookDto {

  private String name;
  private Long stock;
  private Double price;
}
