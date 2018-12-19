package com.omnicuris.ecommerce.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 * Entity which represent Order, which have respective user who placed Order and
 * items placed in order
 * 
 * @author Onkar Saravade
 *
 */
@Entity
public class OrderData extends AbstractBaseEntity {

  private static final long serialVersionUID = -7943546809598181190L;

  private String userEmail;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "order_data_items")
  private Set<OrderedItem> orderedItems = new HashSet<>();

  public void addOrderedItem(final OrderedItem item) {
    this.orderedItems.add(item);
  }

  public Set<OrderedItem> getOrderedItems() {
    return orderedItems;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setOrderedItems(final Set<OrderedItem> orderedItems) {
    this.orderedItems = orderedItems;
  }

  public void setUserEmail(final String userEmail) {
    this.userEmail = userEmail;
  }
}
