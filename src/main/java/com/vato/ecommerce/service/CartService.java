package com.vato.ecommerce.service;

import com.vato.ecommerce.exceptions.CartNotFoundException;
import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.model.dto.AddItemRequest;
import com.vato.ecommerce.model.entity.Cart;
import com.vato.ecommerce.model.entity.CartItem;
import com.vato.ecommerce.model.entity.Product;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

// TODO: Make better exception descriptions
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemService cartItemService;

    public CartService(
            CartRepository cartRepository,
            ProductService productService,
            CartItemService cartItemService
    ) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    public String addCartItem(Long userId, AddItemRequest request) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        Product product = productService.findProductById(request.productId());
        CartItem cartItemExists = cartItemService.cartItemExists(cart, product, request.size(), userId);

        if (cartItemExists == null) {
            int price = request.quantity() * product.getDiscountedPrice();
            CartItem cartItem = CartItem.builder()
                    .product(product)
                    .cart(cart)
                    .quantity(request.quantity())
                    .userId(userId)
                    .price(price)
                    .size(request.size())
                    .build();

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }

        return "Item added to cart";
    }

    public Cart findUserCart(Long userId) throws CartNotFoundException {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        int totalPrice = 0, totalDiscountedPrice = 0, totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setDiscount(totalPrice - totalDiscountedPrice);

        return cartRepository.save(cart);
    }
}
