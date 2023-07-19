package com.poly.fman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.ProductSize;

public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {


	public List<ProductSize> findProductSizeByProductId(String id);
  
    public Optional<ProductSize> findAllByActiveIsTrue();


 
    
}
