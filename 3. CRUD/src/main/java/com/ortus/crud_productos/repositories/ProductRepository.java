package com.ortus.crud_productos.repositories;

import com.ortus.crud_productos.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    Product findByNameContainingIgnoreCase(String name);

}
