package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.entity.Product;
import com.vato.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
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

    @Operation(summary = "Find Product by Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Page<Product>> findProductByCategory(
            @RequestParam("category") String category,
            @RequestParam("color") List<String> color,
            @RequestParam("size") List<String> size,
            @RequestParam("minPrice") Integer minPrice,
            @RequestParam("maxPrice") Integer maxPrice,
            @RequestParam("minDiscount") Integer minDiscount,
            @RequestParam("sort") String sort,
            @RequestParam("stock") String stock,
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize
    ) {
        Page<Product> response = productService.getAllProducts(
                category,
                color,
                size,
                minPrice,
                maxPrice,
                minDiscount,
                sort,
                stock,
                pageNumber,
                pageSize
        );

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get Product By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)
                    )
            )
    })
    @GetMapping("/id/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        Product product = productService.findProductById(productId);

        return ResponseEntity.ok(product);
    }
}
