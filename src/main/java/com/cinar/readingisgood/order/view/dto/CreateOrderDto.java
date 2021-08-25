package com.cinar.readingisgood.order.view.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderDto {

  private String bookName;
  private Long count;
  private String customerEmail;
}
