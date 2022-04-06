package com.sk.shopcart.persistence.repository;

import com.sk.shopcart.persistence.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
  public Shop findByIdentifier(String identifier);
}
