package com.omnicuris.ecommerce.repository;

import com.omnicuris.ecommerce.entity.Item;
import com.omnicuris.ecommerce.entity.ItemQuantity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link ItemQuantity} entity
 * 
 * @author Onkar Saravade
 *
 */
@Repository
public interface ItemQuantityRepository extends
    JpaRepository<ItemQuantity, String> {

  ItemQuantity findByItem(final Item item);

}
