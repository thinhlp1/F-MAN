package com.poly.fman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {

        //find all brand -- active = true
    public Optional<List<Brand>> findAllByActiveIsTrue(); 

    public Optional<Brand> findByName(String name);

    public Optional<List<Brand>> findByNameLike(String name);
    
    boolean existsById(String id);
}
