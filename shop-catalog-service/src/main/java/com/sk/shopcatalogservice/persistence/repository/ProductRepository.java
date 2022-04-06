package com.sk.shopcatalogservice.persistence.repository;

import com.sk.shopcatalogservice.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findByIdentifier(String identifier);
}
