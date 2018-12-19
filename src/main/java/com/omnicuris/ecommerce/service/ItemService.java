package com.omnicuris.ecommerce.service;

import com.omnicuris.ecommerce.dto.ItemDto;
import com.omnicuris.ecommerce.entity.Item;
import com.omnicuris.ecommerce.entity.ItemQuantity;
import com.omnicuris.ecommerce.repository.ItemQuantityRepository;
import com.omnicuris.ecommerce.repository.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * Service which has business logic of Item related opeations.
 * 
 * @author Onkar Saravade
 *
 */
@Service
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemQuantityRepository itemQuantityRepository;

  public ItemService(final ItemRepository itemRepository,
      final ItemQuantityRepository itemQuantityRepository) {
    this.itemRepository = itemRepository;
    this.itemQuantityRepository = itemQuantityRepository;
  }

  public ItemDto createItem(final ItemDto itemDto) {
    Item item = this.createOrUpdateItemFromDto(itemDto);
    final ItemQuantity itemQuantity =
        this.createQuantity(item, itemDto.getQuantity());
    item.setItemQuantity(itemQuantity);
    item = itemRepository.save(item);
    return new ItemDto(item.getUuid());
  }

  private Item createOrUpdateItemFromDto(final ItemDto itemDto) {
    Item item = null;
    if (itemDto.getUuid() != null) {
      final Optional<Item> optionalItem =
          itemRepository.findById(itemDto.getUuid());
      if (optionalItem.isPresent()) {
        item = optionalItem.get();
      } else {
        // TODO: throw exception
      }
    } else {
      item = new Item();
    }
    item.setName(itemDto.getName());
    item.setDescription(itemDto.getDescription());
    return item;
  }

  public ItemQuantity createQuantity(final Item item, final int quantity) {
    final ItemQuantity itemQuantity = new ItemQuantity();
    itemQuantity.setItem(item);
    itemQuantity.setQuantity(quantity);
    return itemQuantity;
  }

  public boolean deleteItem(final String itemUuid) {
    itemRepository.deleteById(itemUuid);
    return true;
  }

  public List<ItemDto> getAllItems() {
    final List<Item> items = itemRepository.findAll();
    return items.stream().map(item -> new ItemDto(item)).collect(
        Collectors.toList());
  }

  public ItemDto getItemByUuid(final String itemUuid) {
    final Item item = itemRepository.findByUuid(itemUuid);
    return new ItemDto(item);
  }

  public boolean updateItem(final ItemDto itemDto) {
    final Item item = this.createOrUpdateItemFromDto(itemDto);
    item.setItemQuantity(this.updateQuantity(item, itemDto.getQuantity()));
    itemRepository.save(item);
    return true;
  }

  public ItemQuantity updateQuantity(final Item item, final int quantity) {
    final ItemQuantity itemQuantity = itemQuantityRepository.findByItem(item);
    itemQuantity.setQuantity(quantity);
    return itemQuantity;
  }

}
