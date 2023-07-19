package com.poly.fman.repository;

import com.poly.fman.entity.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, String>{
    //find all size -- active = true
    public Optional<List<Size>> findAllByActiveIsTrue();

    //Find all size active is true with Page
    public Optional<Page<Size>> findAllByActiveIsTrue(Pageable pageable);

    //Find all size active is false with Page
    public Page<Size> findByActiveFalse(Pageable pageable);

    //Find Size by size field
    public Optional<Size> findBySize(Float size);

    //Check size existed
    boolean existsById(String id);
    boolean existsBySize(Float size);


    public Page<Size> findByIdContainingIgnoreCaseAndActiveIsTrue(String id, Pageable pageable);


    @Query("SELECT s FROM Size s WHERE s.size = :keyword OR s.length = :keyword OR s.width = :keyword")
    Page<Size> findBySizeOrWidthOrLength(@Param("keyword") Float keyword, Pageable pageable);



}
