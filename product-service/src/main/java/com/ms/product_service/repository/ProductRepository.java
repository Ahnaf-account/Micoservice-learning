package com.ms.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.product_service.entitty.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
