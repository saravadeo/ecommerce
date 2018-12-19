package com.omnicuris.ecommerce.repository;

import com.omnicuris.ecommerce.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link Item} entity
 * 
 * @author Onkar Saravade
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

  Item findByUuid(final String itemUuid);

}
