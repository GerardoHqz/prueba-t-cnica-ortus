package com.ortus.crud_productos.services.impl;

import com.ortus.crud_productos.models.dtos.ProductDTO;
import com.ortus.crud_productos.models.dtos.UpdateProductDTO;
import com.ortus.crud_productos.models.entities.Product;
import com.ortus.crud_productos.repositories.ProductRepository;
import com.ortus.crud_productos.services.ProductService;
import com.ortus.crud_productos.utils.CustomException;
import com.ortus.crud_productos.utils.ErrorType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProduct(ProductDTO data) {
        Product productExisting = productRepository.findByNameContainingIgnoreCase(data.getName());
        if(productExisting != null){
            throw new CustomException(ErrorType.DUPLICATE_ENTITY, data.getName());
        }
        try{
            Product product = new Product(data.getName(), data.getPrice(), data.getStock());
            productRepository.save(product);
        }catch (IllegalArgumentException e) {
            throw new CustomException(ErrorType.BAD_REQUEST, e.getMessage());
        }catch (Exception e) {
            throw new CustomException(ErrorType.INTERNAL_ERROR, "Error al guardar el producto");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(UpdateProductDTO data) {
        try {
            Product product = productRepository.findById(data.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + data.getId()));

            if (data.getName() != null) {
                product.setName(data.getName());
            }

            if (data.getPrice() != null) {
                product.setPrice(data.getPrice());
            }

            if (data.getStock() != null) {
                product.setStock(data.getStock());
            }

            productRepository.save(product);

        } catch (CustomException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorType.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(ErrorType.INTERNAL_ERROR, "Error al actualizar el producto");
        }
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return Optional.ofNullable(productRepository.findByName(name));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(long id) {
        try {
            productRepository.findById(id)
                    .ifPresentOrElse(productRepository::delete,
                            () -> {
                                throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
                            });
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorType.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(ErrorType.INTERNAL_ERROR, "Error al eliminar el producto");
        }


    }
}