package com.omnicuris.ecommerce.validator.data;

import com.omnicuris.ecommerce.dto.ItemDto;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator to validate ItemDto while creating Item
 * 
 * @author Onkar Saravade
 *
 */
@Service
public class ItemDtoValidator implements Validator {

  @Override
  public boolean supports(final Class<?> clazz) {
    return ItemDto.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(final Object object, final Errors errors) {
    final ItemDto itemDto = (ItemDto) object;

    if (itemDto.getName() == null) {
      errors.reject("Item must have name.");
    }

    if (itemDto.getQuantity() <= 0) {
      errors.reject("Item must be greater than 0.");
    }
  }

}
