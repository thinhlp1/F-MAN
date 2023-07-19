package com.poly.fman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.Size;
import com.poly.fman.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{
    public Transaction findByOrderId(Integer id);
}
