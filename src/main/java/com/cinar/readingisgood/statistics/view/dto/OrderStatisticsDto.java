package com.cinar.readingisgood.statistics.view.dto;

import com.cinar.readingisgood.order.core.domain.OrderDomain;
import java.util.List;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderStatisticsDto {

  private String month;
  private Long totalOrderCount;
  private Double totalPurchasedAmount;

  public static OrderStatisticsDto fromOrderStatisticsMap(Entry<String, List<OrderDomain>> entry) {
    return OrderStatisticsDto.builder()
        .month(entry.getKey())
        .totalOrderCount((long) entry.getValue().size())
        .totalPurchasedAmount(entry.getValue().stream().mapToDouble(OrderDomain::getAmount).sum())
        .build();
  }
}
