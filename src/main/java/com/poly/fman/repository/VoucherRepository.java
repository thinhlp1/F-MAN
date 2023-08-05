package com.poly.fman.repository;

import com.poly.fman.entity.Voucher;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    boolean existsByName(String name);
    List<Voucher> findByActiveOrderByCreateAtDesc(boolean active);

    Optional<Voucher> findByNameAndActiveIsTrue(String voucherName);
    List<Voucher> findAllByActiveIsTrue();

    Voucher findByIdAndActiveIsTrue(String id);

    Page<Voucher> findByEndAtLessThanEqual(Date endDate, Pageable pageable);

    Page<Voucher> findByStartAtGreaterThanEqual(Date currentDate, Pageable pageable);
}
