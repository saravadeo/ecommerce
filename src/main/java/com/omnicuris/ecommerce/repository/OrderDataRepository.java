package com.omnicuris.ecommerce.repository;

import com.omnicuris.ecommerce.entity.OrderData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link OrderData} entity
 * 
 * @author Onkar Saravade
 *
 */
@Repository
public interface OrderDataRepository extends JpaRepository<OrderData, String> {

  OrderData findByUuid(final String uuid);

}
