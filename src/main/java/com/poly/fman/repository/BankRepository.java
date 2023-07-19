package com.poly.fman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, String> {
    
}
