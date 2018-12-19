package com.omnicuris.ecommerce.controller;

import com.omnicuris.ecommerce.dto.ItemDto;
import com.omnicuris.ecommerce.exception.RestApiException;
import com.omnicuris.ecommerce.service.ItemService;
import com.omnicuris.ecommerce.validator.data.ItemDtoValidator;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing Item CRUD operation
 * 
 * @author Onkar Saravade
 *
 */
@RestController
@RequestMapping("/item")
public class ItemController {

  private final ItemService itemService;
  private final ItemDtoValidator itemDtoValidator;

  public ItemController(final ItemService itemService,
      final ItemDtoValidator itemDtoValidator) {
    this.itemService = itemService;
    this.itemDtoValidator = itemDtoValidator;
  }

  @PostMapping("/create")
  public ItemDto createItem(@RequestBody final ItemDto itemDto,
      final BindingResult result) {
    itemDtoValidator.validate(itemDto, result);
    if (result.hasErrors()) {
      throw new RestApiException("Item details not correct",
          HttpStatus.BAD_REQUEST);
    }
    return itemService.createItem(itemDto);

  }

  @GetMapping("/delete")
  public boolean deleteItem(@RequestParam("uuid") final String itemUuid) {
    if (itemUuid == null) {
      throw new RestApiException("Uuid must not be null.",
          HttpStatus.BAD_REQUEST);
    }
    return itemService.deleteItem(itemUuid);
  }

  @GetMapping("/all")
  public List<ItemDto> getAllItems() {
    return itemService.getAllItems();
  }

  @GetMapping
  public ItemDto getItem(@RequestParam("uuid") final String itemUuid) {
    if (itemUuid == null) {
      throw new RestApiException("Uuid must not be null.",
          HttpStatus.BAD_REQUEST);
    }
    return itemService.getItemByUuid(itemUuid);
  }

  @PostMapping("/update")
  public boolean updateItem(@RequestBody final ItemDto itemDto,
      final BindingResult result) {
    itemDtoValidator.validate(itemDto, result);
    if (result.hasErrors()) {
      throw new RestApiException("Item details not correct",
          HttpStatus.BAD_REQUEST);
    }
    return itemService.updateItem(itemDto);

  }

}
