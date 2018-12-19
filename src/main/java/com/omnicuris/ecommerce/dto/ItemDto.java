package com.omnicuris.ecommerce.dto;

import com.omnicuris.ecommerce.entity.Item;

import java.io.Serializable;

/**
 * Dto for creating item and retrieving item.
 * 
 * @author Onkar Saravade
 *
 */
public class ItemDto implements Serializable {

  private static final long serialVersionUID = -3884475083610550379L;

  private String uuid;

  private String name;

  private String description;

  private int quantity;

  public ItemDto() {
  }

  public ItemDto(final Item item) {
    this(item.getUuid());
    this.name = item.getName();
    this.description = item.getDescription();
    this.quantity = item.getItemQuantity().getQuantity();
    
  }

  public ItemDto(final String uuid) {
    this.uuid = uuid;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getUuid() {
    return uuid;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setQuantity(final int quantity) {
    this.quantity = quantity;
  }

  public void setUuid(final String uuid) {
    this.uuid = uuid;
  }

}
