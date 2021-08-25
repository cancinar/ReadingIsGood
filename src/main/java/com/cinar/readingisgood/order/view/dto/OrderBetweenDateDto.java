package com.cinar.readingisgood.order.view.dto;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class OrderBetweenDateDto {
  @DateTimeFormat(iso = DATE)
  private LocalDateTime startDate;
  @DateTimeFormat(iso = DATE)
  private LocalDateTime endDate;
}
