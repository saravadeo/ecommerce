package com.omnicuris.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Order dto which has all order details
 * 
 * @author Onkar Saravade
 *
 */
public class OrderDto implements Serializable {

  private static final long serialVersionUID = -1824321041135759406L;

  private String uuid;

  private String userEmail;

  private List<OrderItemDto> items;

  public OrderDto() {

  }

  public OrderDto(final String uuid) {
    this.uuid = uuid;
  }

  public List<OrderItemDto> getItems() {
    return items;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public String getUuid() {
    return uuid;
  }

  public void setItems(final List<OrderItemDto> items) {
    this.items = items;
  }

  public void setUserEmail(final String userEmail) {
    this.userEmail = userEmail;
  }

  public void setUuid(final String uuid) {
    this.uuid = uuid;
  }
}
