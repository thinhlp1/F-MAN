package com.poly.fman.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    public Optional<Cart> findByUserId(Integer userId);

}
