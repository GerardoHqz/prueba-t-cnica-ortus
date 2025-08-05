package com.ortus.crud_productos.services;

import com.ortus.crud_productos.models.dtos.ProductDTO;
import com.ortus.crud_productos.models.dtos.UpdateProductDTO;
import com.ortus.crud_productos.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void saveProduct(ProductDTO data);
    void updateProduct(UpdateProductDTO data);
    Optional<Product> getProductById(long id);
    Optional<Product> getProductByName(String name);
    List<Product> getAllProducts();
    void deleteProduct(long id);
}
