package com.cinar.readingisgood.statistics.view;

import com.cinar.readingisgood.statistics.core.StatisticsComponent;
import com.cinar.readingisgood.statistics.view.dto.OrderStatisticsDto;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

  private final StatisticsComponent statisticsComponent;

  @GetMapping("/total-order")
  @ApiOperation(value = "Get total order count", response = Long.class)
  public ResponseEntity<Long> getTotalOrder() {
    return ResponseEntity
        .ok(statisticsComponent
            .getTotalOrderCount());
  }

  @GetMapping("/total-amount")
  public ResponseEntity<Double> getTotalAmount() {
    return ResponseEntity
        .ok(statisticsComponent
            .getTotalAmountOfOrder());
  }

  @GetMapping("/order-details")
  public ResponseEntity<List<OrderStatisticsDto>> getOrderDetailsPerMonth() {
    return ResponseEntity
        .ok(statisticsComponent
            .getOrderDetailPerMonth()
            .entrySet()
            .stream()
            .map(OrderStatisticsDto::fromOrderStatisticsMap)
            .collect(Collectors.toList()));
  }
}
