package com.vato.ecommerce.service;

import com.vato.ecommerce.exceptions.CartItemNotFoundException;
import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.entity.Cart;
import com.vato.ecommerce.model.entity.CartItem;
import com.vato.ecommerce.model.entity.Product;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.repository.CartItemRepository;
import com.vato.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    public CartItemService(CartItemRepository cartItemRepository, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
    }

    public CartItem findCartItemById(Long cartItemId) throws CartItemNotFoundException {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item with id " + cartItemId + " not found"));
    }

    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(
                cartItem.getProduct().getPrice() * cartItem.getQuantity()
        );
        cartItem.setDiscountedPrice(
                cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity()
        );

        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws UserNotFoundException, CartItemNotFoundException {
        CartItem item = findCartItemById(cartItemId);
        User user = userService.findUserById(item.getUserId());

        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(
                    item.getQuantity() * item.getProduct().getPrice()
            );
            item.setDiscountedPrice(
                    item.getProduct().getDiscountedPrice() * item.getQuantity()
            );
        }

        return cartItemRepository.save(item);
    }

    public CartItem cartItemExists(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.cartItemExists(cart, product, size, userId)
                .orElse(null);
    }

    public String removeCartItem(Long userId, Long cartItemId) throws UserNotFoundException, CartItemNotFoundException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        User reqUser = userService.findUserById(userId);

        if (user.getId().equals(reqUser.getId())) {
            cartItemRepository.deleteById(cartItemId);
            return "Item removed successfully";
        } else
            throw new UserNotFoundException("You can't remove another user's item");
    }
}
