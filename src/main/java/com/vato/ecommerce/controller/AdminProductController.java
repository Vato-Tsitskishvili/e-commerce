package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.model.dto.ApiResponse;
import com.vato.ecommerce.model.dto.CreateProductRequest;
import com.vato.ecommerce.model.entity.Product;
import com.vato.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(request);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        String message = productService.deleteProduct(productId);
        ApiResponse response = new ApiResponse(message, true);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(
            @RequestBody Product product,
            @PathVariable("productId") Long productId
    ) throws ProductNotFoundException {
        Product updatedProduct = productService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] requests) {
        for (CreateProductRequest request : requests) {
            productService.createProduct(request);
        }
        ApiResponse response = new ApiResponse("all products created", true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
