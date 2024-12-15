package com.vato.ecommerce.repository;

import com.vato.ecommerce.model.entity.Cart;
import com.vato.ecommerce.model.entity.CartItem;
import com.vato.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(
            "SELECT ci " +
            "FROM CartItem ci " +
            "WHERE ci.cart = :cart " +
            "AND ci.product = :product " +
            "AND ci.size = :size " +
            "AND ci.userId = :userId"
    )
    Optional<CartItem> cartItemExists(
            @Param("cart") Cart cart,
            @Param("product") Product product,
            @Param("size") String size,
            @Param("userId") Long userId
    );
}
