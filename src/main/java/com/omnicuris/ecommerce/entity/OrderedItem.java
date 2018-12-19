package com.omnicuris.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Entity which represent ordered item which contains how many items are ordered
 * with their quantity
 * 
 * @author Onkar Saravade
 *
 */
@Entity
public class OrderedItem extends AbstractBaseEntity {

  private static final long serialVersionUID = -3769797015087471186L;

  @OneToOne
  private Item item;

  private int quantity;

  public Item getItem() {
    return item;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setItem(final Item item) {
    this.item = item;
  }

  public void setQuantity(final int quantity) {
    this.quantity = quantity;
  }

}
