package com.poly.fman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.fman.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    public Optional<List<CartItem>> findByCartIdAndProductSizeActiveIsTrue(Integer cardId);

    public Optional<List<CartItem>> findByProductIdAndCartId(String productId, String cardId);

    public Optional<CartItem> findByProductIdAndAndProductSizeIdAndCartId(String productId, Integer productSizeId,
            Integer cardId);

    public Optional<List<CartItem>> findByProductSizeId(Integer productSizeId);        

    @Query("SELECT COUNT(DISTINCT oi.productSize) FROM CartItem oi WHERE oi.cart.id = :cartId AND oi.productSize.active = true ")
    public int countDistinctProductSizesByCartId(@Param("cartId") int cartId);

}
