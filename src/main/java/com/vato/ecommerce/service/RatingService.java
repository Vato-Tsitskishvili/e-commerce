package com.vato.ecommerce.service;

import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.model.dto.RatingRequest;
import com.vato.ecommerce.model.entity.Product;
import com.vato.ecommerce.model.entity.Rating;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingService {

    private final ProductService productService;
    private final RatingRepository ratingRepository;

    public RatingService(ProductService productService, RatingRepository ratingRepository) {
        this.productService = productService;
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(RatingRequest request, User user) throws ProductNotFoundException {
        Product product = productService.findProductById(request.productId());
        Rating rating = Rating.builder()
                .product(product)
                .user(user)
                .rating(request.rating())
                .createdAt(LocalDateTime.now())
                .build();

        return ratingRepository.save(rating);
    }

    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
