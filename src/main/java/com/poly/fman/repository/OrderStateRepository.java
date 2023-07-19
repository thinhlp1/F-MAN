package com.poly.fman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.OrderState;

public interface OrderStateRepository extends JpaRepository<OrderState, String> {
    
}
