package com.omnicuris.ecommerce.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Entity which represent Item which has name, description and Quantity
 * 
 * @author Onkar Saravade
 *
 */
@Entity
public class Item extends AbstractBaseEntity {

  private static final long serialVersionUID = -2240142467677178635L;

  private String name;

  private String description;

  @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
  private ItemQuantity itemQuantity;

  public String getDescription() {
    return description;
  }

  public ItemQuantity getItemQuantity() {
    return itemQuantity;
  }

  public String getName() {
    return name;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public void setItemQuantity(final ItemQuantity itemQuantity) {
    this.itemQuantity = itemQuantity;
  }

  public void setName(final String name) {
    this.name = name;
  }

}
