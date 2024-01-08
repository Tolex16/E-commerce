package com.example.ecommerce.Repository;

import com.example.ecommerce.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {

   // Optional<Products> findAllProducts(Long productId);
}
