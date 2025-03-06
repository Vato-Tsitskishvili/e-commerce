package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.dto.ReviewRequest;
import com.vato.ecommerce.model.entity.Review;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.ReviewService;
import com.vato.ecommerce.service.UserService;
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
@RequestMapping("/api/reviews")
public class ReviewController {

    private final UserService userService;
    private final ReviewService reviewService;

    public ReviewController(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @Operation(summary = "Create Review")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Review.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<Review> createReview(
            @RequestBody ReviewRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, ProductNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(request, user);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Product Review")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Review.class)
                            )
                    )
            )
    })
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReview(@PathVariable("productId") Long productId) {
        List<Review> reviews = reviewService.getAllReviews(productId);

        return ResponseEntity.ok(reviews);
    }
}
