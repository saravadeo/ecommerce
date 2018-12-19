/**
 * Copyright (C) 2016-2018 KyePot - All Rights Reserved Unauthorized copying of this file, via any medium
 * is strictly prohibited Proprietary and confidential.
 */
package com.omnicuris.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

/**
 * Entity to represent Quantity of respective Item
 * 
 * @author Onkar Saravade
 *
 */
@Entity
public class ItemQuantity extends AbstractBaseEntity{

  private static final long serialVersionUID = -618081733850330453L;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Item item;
  
  @Min(value = 0)
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
