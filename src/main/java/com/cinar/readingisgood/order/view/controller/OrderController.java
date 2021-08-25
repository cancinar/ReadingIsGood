package com.cinar.readingisgood.order.view.controller;

import com.cinar.readingisgood.order.core.OrderComponent;
import com.cinar.readingisgood.order.core.domain.OrderDomain;
import com.cinar.readingisgood.order.view.dto.CreateOrderDto;
import com.cinar.readingisgood.order.view.dto.OrderDto;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderComponent orderComponent;

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(
      @RequestBody CreateOrderDto createOrderDto) {
    final OrderDomain orderDomain = orderComponent
        .createOrder(createOrderDto.getBookName(), createOrderDto.getCount(),
            createOrderDto.getCustomerEmail());

    return ResponseEntity.ok(OrderDto.fromOrderDomain(orderDomain));
  }

  @GetMapping("/between")
  public ResponseEntity<List<OrderDto>> findOrdersByDate(
      @RequestParam(name = "startDate") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
      @RequestParam(name = "endDate") @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
    final List<OrderDomain> orderDomains = orderComponent
        .findAllByDate(startDate, endDate);

    return ResponseEntity
        .ok(orderDomains.stream().map(OrderDto::fromOrderDomain).collect(Collectors.toList()));
  }

  @GetMapping
  public ResponseEntity<List<OrderDto>> findCustomerOrdersInPage(@RequestParam String customerEmail,
      @RequestParam int page) {
    final List<OrderDomain> orderDomains = orderComponent.findByCustomerName(customerEmail, page);

    return ResponseEntity
        .ok(orderDomains
            .stream()
            .map(OrderDto::fromOrderDomain)
            .collect(Collectors.toList()));
  }

}
