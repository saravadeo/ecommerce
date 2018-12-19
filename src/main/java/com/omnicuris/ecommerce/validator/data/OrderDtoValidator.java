package com.omnicuris.ecommerce.validator.data;

import com.omnicuris.ecommerce.dto.OrderDto;
import com.omnicuris.ecommerce.dto.OrderItemDto;
import com.omnicuris.ecommerce.exception.RestApiException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator to validate OrderDto while creating or placing order.
 * 
 * @author Onkar Saravade
 *
 */
@Service
public class OrderDtoValidator implements Validator {

  @Override
  public boolean supports(final Class<?> clazz) {
    return OrderDto.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(final Object arg0, final Errors arg1) {
    final OrderDto orderDto = (OrderDto) arg0;

    if (orderDto.getUserEmail() == null) {
      throw new RestApiException("Users email must not be null.",
          HttpStatus.BAD_REQUEST);
    }

    final List<OrderItemDto> orderItemDtos = orderDto.getItems();

    orderItemDtos.stream().forEach(
        itemDto -> {
          if (itemDto.getUuid() == null || itemDto.getQuantity() <= 0) {
            throw new RestApiException(
                "Order should have correct item with minimum 1 quantity.",
                HttpStatus.BAD_REQUEST);
          }
        });

  }

}
