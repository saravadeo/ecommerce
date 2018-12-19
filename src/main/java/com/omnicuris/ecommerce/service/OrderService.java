package com.omnicuris.ecommerce.service;

import com.omnicuris.ecommerce.dto.OrderDto;
import com.omnicuris.ecommerce.dto.OrderItemDto;
import com.omnicuris.ecommerce.entity.Item;
import com.omnicuris.ecommerce.entity.ItemQuantity;
import com.omnicuris.ecommerce.entity.OrderData;
import com.omnicuris.ecommerce.entity.OrderedItem;
import com.omnicuris.ecommerce.exception.RestApiException;
import com.omnicuris.ecommerce.repository.ItemQuantityRepository;
import com.omnicuris.ecommerce.repository.ItemRepository;
import com.omnicuris.ecommerce.repository.OrderDataRepository;
import com.omnicuris.ecommerce.repository.OrderedItemRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to manage business logic for order
 * 
 * @author Onkar Saravade
 *
 */
@Service
public class OrderService {

  private final OrderDataRepository orderDataRepository;
  private final ItemRepository itemRepository;
  private final ItemQuantityRepository itemQuantityRepository;
  private final OrderedItemRepository orderedItemRepository;

  public OrderService(final OrderDataRepository orderDataRepository,
      final ItemRepository itemRepository,
      final ItemQuantityRepository itemQuantityRepository,
      final OrderedItemRepository orderedItemRepository) {
    this.orderDataRepository = orderDataRepository;
    this.itemRepository = itemRepository;
    this.itemQuantityRepository = itemQuantityRepository;
    this.orderedItemRepository = orderedItemRepository;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public OrderDto createOrder(final OrderDto orderDto) {

    OrderData orderData = new OrderData();
    orderData.setUserEmail(orderDto.getUserEmail());

    final List<OrderItemDto> orderItemDtos = orderDto.getItems();

    final Set<OrderedItem> orderedItems =
        orderItemDtos.stream().map(
            orderItemDto -> {
              final Item item =
                  itemRepository.findByUuid(orderItemDto.getUuid());
              final int quantity = orderItemDto.getQuantity();
              final OrderedItem orderedItem = new OrderedItem();
              orderedItem.setItem(item);
              orderedItem.setQuantity(quantity);
              final ItemQuantity itemQuantity = item.getItemQuantity();
              final int totalAvailabel = itemQuantity.getQuantity();
              final int remaining = totalAvailabel - quantity;
              if (remaining < 0) {
                throw new RestApiException(String.format(
                    "Item %s is out of stock. %s remaining item.",
                    item.getName(), totalAvailabel), HttpStatus.BAD_REQUEST);
              }
              itemQuantity.setQuantity(totalAvailabel - quantity);
              itemQuantityRepository.save(itemQuantity);
              return orderedItemRepository.save(orderedItem);
            }).collect(Collectors.toSet());

    orderData.getOrderedItems().addAll(orderedItems);
    orderData = orderDataRepository.save(orderData);

    return new OrderDto(orderData.getUuid());

  }

  public OrderDto getOrder(final String uuid) {
    final OrderData orderData = orderDataRepository.findByUuid(uuid);
    final OrderDto orderDto = new OrderDto();
    orderDto.setUuid(orderData.getUuid());
    orderDto.setUserEmail(orderData.getUserEmail());
    final Set<OrderedItem> orderedItems = orderData.getOrderedItems();

    final List<OrderItemDto> orderItemDtos =
        orderedItems.stream().map(item -> {
          final OrderItemDto orderItemDto = new OrderItemDto();
          orderItemDto.setUuid(item.getItem().getUuid());
          orderItemDto.setName(item.getItem().getName());
          orderItemDto.setDescription(item.getItem().getDescription());
          orderItemDto.setQuantity(item.getQuantity());
          return orderItemDto;
        }).collect(Collectors.toList());

    orderDto.setItems(orderItemDtos);
    return orderDto;
  }
}
