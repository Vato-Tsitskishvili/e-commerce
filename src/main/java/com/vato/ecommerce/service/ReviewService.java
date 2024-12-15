package com.vato.ecommerce.service;

import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.model.dto.ReviewRequest;
import com.vato.ecommerce.model.entity.Product;
import com.vato.ecommerce.model.entity.Review;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final ProductService productService;
    private final ReviewRepository reviewRepository;

    public ReviewService(ProductService productService, ReviewRepository reviewRepository) {
        this.productService = productService;
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(ReviewRequest request, User user) throws ProductNotFoundException {
        Product product = productService.findProductById(request.productId());
        Review review = Review.builder()
                .user(user)
                .product(product)
                .review(request.review())
                .createdAt(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
