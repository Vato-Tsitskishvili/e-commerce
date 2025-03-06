package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.model.dto.CreateProductRequest;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.entity.Product;
import com.vato.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to create a product"
            )
    })
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(request);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product has been deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to delete product"
            )
    })
    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<Response> deleteProduct(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        String message = productService.deleteProduct(productId);
        Response response = new Response(message, true);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get All Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Product.class)
                            )
                    )
            )
    })
    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @Operation(summary = "Update Product by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product has been updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to update product"
            )
    })
    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(
            @RequestBody Product product,
            @PathVariable("productId") Long productId
    ) throws ProductNotFoundException {
        Product updatedProduct = productService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "Create Multiple Products")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All products have been created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to create all products"
            )
    })
    @PostMapping("/create")
    public ResponseEntity<Response> createMultipleProduct(@RequestBody CreateProductRequest[] requests) {
        for (CreateProductRequest request : requests) {
            productService.createProduct(request);
        }
        Response response = new Response("all products created", true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
