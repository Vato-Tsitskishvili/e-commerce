package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.dto.RatingRequest;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.entity.Rating;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.RatingService;
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
@RequestMapping("/api/ratings")
public class RatingController {

    private final UserService userService;
    private final RatingService ratingService;

    public RatingController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @Operation(summary = "Create Rating")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Rating.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(
            @RequestBody RatingRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, ProductNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(request, user);

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Products' Rating")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Rating.class)
                            )
                    )
            )
    })
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(
            @PathVariable("productId") Long productId,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException {
        userService.findUserProfileByJwt(jwt);
        List<Rating> ratings = ratingService.getProductsRating(productId);

        return ResponseEntity.ok(ratings);
    }
}
