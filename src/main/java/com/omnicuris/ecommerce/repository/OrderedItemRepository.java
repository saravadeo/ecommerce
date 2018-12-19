package com.omnicuris.ecommerce.repository;

import com.omnicuris.ecommerce.entity.OrderedItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link OrderedItem} entity
 * 
 * @author Onkar Saravade
 *
 */
@Repository
public interface OrderedItemRepository extends
    JpaRepository<OrderedItem, String> {

}
