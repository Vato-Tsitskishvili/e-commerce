package com.vato.ecommerce.service;

import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.model.dto.CreateProductRequest;
import com.vato.ecommerce.model.entity.Category;
import com.vato.ecommerce.model.entity.Product;
import com.vato.ecommerce.repository.CategoryRepository;
import com.vato.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public Product createProduct(CreateProductRequest request) {
        Category topLevel = categoryRepository.findByName(request.topLevelCategory())
                .orElseGet(() -> Category.builder()
                        .name(request.topLevelCategory())
                        .level(1)
                        .build()
                );
        Category secondLevel = categoryRepository.findByNameAndParentCategoryName(
                    request.secondLevelCategory(),
                    topLevel.getName()
                ).orElseGet(() -> Category.builder()
                        .parentCategory(topLevel)
                        .name(request.secondLevelCategory())
                        .level(2)
                        .build()
                );
        Category thirdLevel = categoryRepository.findByNameAndParentCategoryName(
                    request.thirdLevelCategory(),
                    secondLevel.getName()
                ).orElseGet(() -> Category.builder()
                        .parentCategory(secondLevel)
                        .name(request.thirdLevelCategory())
                        .level(3)
                        .build()
                );

        Product product = Product.builder()
                .title(request.title())
                .color(request.color())
                .description(request.description())
                .discountedPrice(request.discountedPrice())
                .discountPercent(request.discountPercent())
                .imageUrl(request.imageUrl())
                .brand(request.brand())
                .price(request.price())
                .sizes(request.sizes())
                .quantity(request.quantity())
                .category(thirdLevel)
                .createdAt(LocalDateTime.now())
                .build();

        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product newProduct) throws ProductNotFoundException {
        Product product = findProductById(productId);

        if (newProduct.getQuantity() != 0)
            product.setQuantity(newProduct.getQuantity());

        return productRepository.save(product);
    }

    public String deleteProduct(Long productId) throws ProductNotFoundException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted Successfully";
    }

    public Product findProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    public Page<Product> getAllProducts(
            String category,
            List<String> colors,
            List<String> sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber,
            Integer pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProducts(
                category,
                minPrice,
                maxPrice,
                minDiscount,
                sort
        );

        if (!colors.isEmpty())
            products = products.stream()
                    .filter(product -> colors.stream()
                            .anyMatch(color -> color.equalsIgnoreCase(product.getColor()))
                    )
                    .toList();

        if (stock != null) {
            if (stock.equals("in_stock"))
                products = products.stream()
                        .filter(product -> product.getQuantity() > 0)
                        .toList();

            else if (stock.equals("out_of_stock"))
                products = products.stream()
                        .filter(product -> product.getQuantity() < 1)
                        .toList();
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
        List<Product> pageContent = products.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, pageable, products.size());
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
