package com.ortus.crud_productos.controllers;

import com.ortus.crud_productos.models.dtos.MessageDTO;
import com.ortus.crud_productos.models.dtos.ProductDTO;
import com.ortus.crud_productos.models.dtos.UpdateProductDTO;
import com.ortus.crud_productos.models.entities.Product;
import com.ortus.crud_productos.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<MessageDTO> saveEmploye(@RequestBody @Valid ProductDTO data) {
        productService.saveProduct(data);
        return new ResponseEntity<>(new MessageDTO("Producto creado exitosamente"), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageDTO> updateEmploye(@RequestBody @Valid UpdateProductDTO data) {
        productService.updateProduct(data);
        return new ResponseEntity<>(new MessageDTO("Producto actualizado exitosamente"), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) Long idProduct,
                                                @RequestParam(required = false) String name) {
        if (idProduct != null) {
            return productService.getProductById(idProduct)
                    .map(p -> ResponseEntity.ok(List.of(p)))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of()));
        }

        if (name != null) {
            return productService.getProductByName(name)
                    .map(p -> ResponseEntity.ok(List.of(p)))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of()));
        }

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDTO> deleteEmploye(@PathVariable long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new MessageDTO("Producto eliminado exitosamente"), HttpStatus.OK);
    }

}
