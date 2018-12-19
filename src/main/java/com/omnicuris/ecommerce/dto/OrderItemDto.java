package com.omnicuris.ecommerce.dto;

import java.io.Serializable;

/**
 * Single order item dto, which has respective item and its quantity
 * 
 * @author Onkar Saravade
 *
 */
public class OrderItemDto implements Serializable {

  private static final long serialVersionUID = -3688894776747994515L;

  private String uuid;

  private String name;

  private String description;

  private int quantity;

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
