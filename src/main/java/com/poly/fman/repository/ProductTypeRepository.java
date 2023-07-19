package com.poly.fman.repository;

import com.poly.fman.entity.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductTypeRepository  extends JpaRepository<ProductType, String> {
    //find all producttype -- active = true
    public Optional<List<ProductType>> findAllByActiveIsTrue();

    //Find all ProductType active is true
    public Optional<Page<ProductType>> findAllByActiveIsTrue(Pageable pageable);

    //Find all ProductType active is true
    public Page<ProductType> findByActiveFalse(Pageable pageable);

    //Find ProductType by Name field
    public Optional<ProductType> findByName(String name);

    @Query("SELECT p FROM ProductType p WHERE p.id LIKE %:keyword% OR p.name LIKE %:keyword%")
    Page<ProductType> findByIdOrNameContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);

    boolean existsById(String id);
    boolean existsByName(String name);
}
