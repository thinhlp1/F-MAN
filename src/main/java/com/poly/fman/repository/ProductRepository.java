package com.poly.fman.repository;


import java.math.BigInteger;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.poly.fman.entity.Product;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, String> {

    public Optional<Page<Product>> findAllByActiveIsTrue(Pageable pageable);

    public Optional<List<Product>> findAllByActiveIsTrue();

    public Optional<Page<Product>> findAllByActiveIsFalse(Pageable pageable);

    public Optional<Product> findByNameAndActiveIsTrue(String name);

    public Optional<Integer> countProductByActiveIsTrue();

    @Query("SELECT oi.product " +
            "FROM OrderItem oi " +
            "WHERE oi.product.active = 1 " +
            "GROUP BY oi.product, oi.id " +
            "ORDER BY SUM(oi.quantity) DESC")
    Page<Product> findTopSellingActiveProducts(Pageable pageable);

    public Optional<List<Product>> findByNameLikeAndActiveIsTrue(String name);

    public Optional<Page<Product>> findByBrandId(String id, Pageable pageable);

    public Optional<List<Product>> findByBrandId(String id);

    public Optional<Page<Product>> findByNameContaining(String name, Pageable pageable);


    public Optional<Page<Product>> findAllProductByBrandIdLikeAndPriceBetweenAndActiveIsTrue(String brandId, BigInteger min, BigInteger max, Pageable pageable);

    //
    public Optional<List<Product>> findByProductTypeId(String id);

    public Optional<Page<Product>> findAllProductByBrandIdLikeAndProductTypeIdLikeAndPriceBetween(String brandId, String productTypeId, BigInteger min, BigInteger max, Pageable pageable);

    // lọc product

    //
//    public Optional<List<Product>> findByBrandIdInAndActiveIsTrue(Collection<String> brandId);
//
//    public Optional<Product> findByProductTypeAndActiveIsTrue(String productType);
//
//    public Optional<Product> findByProductTypeInAndActiveIsTrue(Collection<String> productType);
    boolean existsById(String id);

    boolean existsByName(String name);
}
