package com.poly.fman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.fman.entity.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {
    public Optional<Page<PaymentMethod>> findAllByActiveIsTrue(Pageable pageable);

    public Optional<PaymentMethod> findByName(String name);

    // @Query("SELECT pm " +
    //         "FROM PaymentMethod pm " +
    //         "WHERE pm.account_number LIKE ?1 AND pm.active = ?2 ")
    // public PaymentMethod findPaymentAccountNumber(String accountNumber, Byte number);

    public Optional<PaymentMethod> findByIdAndActiveIsTrue(String id);

    public List<PaymentMethod> findAllByActiveIsTrue();
}
