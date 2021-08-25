package com.cinar.readingisgood.book.view.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBookStockDto {

  private String name;
  private Long stock;
}
