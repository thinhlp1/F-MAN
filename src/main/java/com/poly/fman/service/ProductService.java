package com.poly.fman.service;

import com.poly.fman.dto.model.ProductDTO2;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductType;
import com.poly.fman.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private ModelMapper modelMapper = new ModelMapper();

    private final ProductRepository productRespository;
    
    @PersistenceContext
    private EntityManager entityManager;

    public void addProduct(Product product) {
        entityManager.createNativeQuery("INSERT INTO product (id, name, price, product_type, brand_id, `desc`, create_at, active, image) " +
                "VALUES (:id, :name, :price, :productType, :brandId, :description, :createdAt, :active, :image)")
                .setParameter("id", product.getId())
                .setParameter("name", product.getName())
                .setParameter("price", product.getPrice())
                .setParameter("productType", product.getProductType().getId())
                .setParameter("brandId", product.getBrand().getId())
                .setParameter("description", product.getDesc())
                .setParameter("createdAt", product.getCreateAt())
                .setParameter("active", product.getActive())
                .setParameter("image", product.getImage())
                .executeUpdate();
    }
    public void updateProduct(Product product) {
    	entityManager.createNativeQuery("UPDATE product " +
                "SET name = :name, price = :price, product_type = :productType, brand_id = :brandId, `desc` = :description, update_at = :updateAt,  image = :image " +
                "WHERE id = :id")
                .setParameter("name", product.getName())
                .setParameter("price", product.getPrice())
                .setParameter("productType", product.getProductType().getId())
                .setParameter("brandId", product.getBrand().getId())
                .setParameter("description", product.getDesc())
                .setParameter("updateAt", product.getUpdateAt())      
                .setParameter("image", product.getImage())
                .setParameter("id", product.getId())
                .executeUpdate();
    }
    
    
    public Page<Product> findProductsWithSize(String brandId, BigInteger minPrice, BigInteger maxPrice, String sizeId, Pageable pageable) {
        String sql = "SELECT p.*, ps.size_id " +
                     "FROM Product p " +
                     "INNER JOIN ProductSize ps ON p.id = ps.productId " +
                     "WHERE p.brandId LIKE :brandId " +
                     "AND p.price BETWEEN :minPrice AND :maxPrice " +
                     "AND ps.sizeId = :sizeId";

        Query query = entityManager.createNativeQuery(sql)
                .setParameter("brandId", "%" + brandId + "%")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .setParameter("sizeId", sizeId);

        int totalRows = query.getResultList().size();

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        @SuppressWarnings("unchecked")
        List<Product> results = query.getResultList();

        return new PageImpl<Product>(results, pageable, totalRows);
    }
    
    public  List<Product> getAllProductsWithSize(String brandId, BigInteger minPrice, BigInteger maxPrice, String sizeId) {
        String sql = "SELECT p.*, ps.size_id " +
                     "FROM Product p " +
                     "INNER JOIN ProductSize ps ON p.id = ps.productId " +
                     "WHERE p.brandId LIKE :brandId " +
                     "AND p.price BETWEEN :minPrice AND :maxPrice " +
                     "AND ps.sizeId = :sizeId";

        Query query = entityManager.createNativeQuery(sql)
                .setParameter("brandId", "%" + brandId + "%")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .setParameter("sizeId", sizeId);

        @SuppressWarnings("unchecked")
        List<Product> results = query.getResultList();

        return results;
    } 
    
    public void deleteProductActive(Product product) {
    	entityManager.createNativeQuery("UPDATE fman.product SET active = :active , delete_at = :deleteAt WHERE id = :id")
                .setParameter("active", (byte) 0) 
                .setParameter("deleteAt", new Date())
                .setParameter("id", product.getId())
                .executeUpdate();
    }
    
    
    public Page<Product> getAllProductByBrandIdLikeAndPriceBetween( String brandId, BigInteger minPrice , BigInteger maxPrice , Pageable pageable) {
    	return productRespository.findAllProductByBrandIdLikeAndPriceBetweenAndActiveIsTrue(brandId, minPrice, maxPrice, pageable).orElse(null);
    }
    
  
    
    public List<Product> getAll() {
        return productRespository.findAll();
    }
    public List<Product> getAllActive() {
        return productRespository.findAllByActiveIsTrue().orElse(null);
    }

    public Integer getCountProduct() {
        return productRespository.countProductByActiveIsTrue().orElse(8);
    }

    public Page<Product> getTopProductSelling(Pageable pageable) {
        return productRespository.findTopSellingActiveProducts(pageable);
    }

    public Page<Product> getAll(Pageable pageable) {
        return productRespository.findAll(pageable);
    }

    public Page<Product> getAllActive(Pageable pageable) {
        return productRespository.findAllByActiveIsTrue(pageable).orElse(null);
    }

    public Page<Product> getAllActiveIsFalse(Pageable pageable) {
        return productRespository.findAllByActiveIsFalse(pageable).orElse(null);
    }

    public Page<Product> getProductByNameContain(String name, Pageable pageable) {
        return productRespository.findByNameContaining(name, pageable).orElse(null);
    }

    public List<Product> getAllProductByBrandId(String id) {
        return productRespository.findByBrandId(id).orElse(null);
    }

    public Page<Product> getAllProductByBrandId(String id, Pageable pageable) {
        return productRespository.findByBrandId(id, pageable).orElse(null);
    }

    public List<Product> getAllProductByProductTypeId(String id) {
        return productRespository.findByProductTypeId(id).orElse(null);
    }
    public Product getById(String id) {
        return productRespository.findById(id).orElse(null);
    }

    public Page<Product> getAllProductByBrandIdLikeAndProductTypeIdLikeAndPriceBetween(String brandId, String productTypeId, BigInteger min, BigInteger max, Pageable pageable) {
        return productRespository.findAllProductByBrandIdLikeAndProductTypeIdLikeAndPriceBetween(brandId,productTypeId,min,max,pageable).orElse(null);
    }
    
 

    public Product getByName(String name) {
        return productRespository.findByNameAndActiveIsTrue(name).orElse(null);
    }

    public Product create(ProductDTO2 productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);     
        return productRespository.save(product);
    }

    public Product update(ProductDTO2 productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setActive((byte) 1);
        return productRespository.save(product);
    }

    public Product delete(String id) {
        Product product = this.getById(id);
        product.setActive((byte) 0);
        return productRespository.save(product);
    }

    public Product restore(String id) {
        Product product = this.getById(id);
        product.setActive((byte) 1);
        return productRespository.save(product);
    }

    public boolean existProductById(String id) {
        return productRespository.existsById(id);
    }

    public boolean existProductByName(String name) {
        return productRespository.existsByName(name);
    }
}
