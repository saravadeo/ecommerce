package com.omnicuris.ecommerce.controller;

import com.omnicuris.ecommerce.dto.OrderDto;
import com.omnicuris.ecommerce.exception.RestApiException;
import com.omnicuris.ecommerce.service.OrderService;
import com.omnicuris.ecommerce.validator.data.OrderDtoValidator;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing Order CRUD operations.
 * 
 * @author Onkar Saravade
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;
  private final OrderDtoValidator orderDtoValidator;

  public OrderController(final OrderService orderService,
      final OrderDtoValidator orderDtoValidator) {
    this.orderService = orderService;
    this.orderDtoValidator = orderDtoValidator;
  }

  @PostMapping("/place")
  public OrderDto createOrder(@RequestBody final OrderDto orderDto,
      final BindingResult result) {
    orderDtoValidator.validate(orderDto, result);
    return orderService.createOrder(orderDto);
  }

  @GetMapping
  public OrderDto getOrder(@RequestParam("uuid") final String orderUuid) {
    if (orderUuid == null) {
      throw new RestApiException("Uuid must not be null.",
          HttpStatus.BAD_REQUEST);
    }
    return orderService.getOrder(orderUuid);
  }
}
