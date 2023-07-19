package com.poly.fman.service;

import com.poly.fman.dto.ProductSizeDTO2;

import com.poly.fman.entity.ProductSize;
import com.poly.fman.repository.ProductSizeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSizeService {
    private ModelMapper modelMapper = new ModelMapper();

    private final ProductSizeRepository productSizeRespository;
    @PersistenceContext
    
    private final EntityManager entityManager;
    
    @Transactional
    public void updateQuantity(ProductSizeDTO2 productSizeDTO) {
    	ProductSize productSize = modelMapper.map(productSizeDTO, ProductSize.class);
    	entityManager.createNativeQuery("UPDATE fman.product_size SET quantity =:quantity , update_at= :update_at WHERE id = :id")
        .setParameter("quantity", productSize.getQuantity())
        .setParameter("update_at", productSize.getUpdateAt())
        .setParameter("id", productSize.getId())        
        .executeUpdate();
    }
    
    
    public List<ProductSize> getAll() {
        return productSizeRespository.findAll();
    }

    public Page<ProductSize> getAll(Pageable pageable) {
        return productSizeRespository.findAll(pageable);
    }


    public List<ProductSize> getAllByProductID(String id) {
        return productSizeRespository.findProductSizeByProductId(id);
    }

    public ProductSize getById(int id) {
        return productSizeRespository.findById(id).orElse(null);
    }


    public ProductSize create(ProductSizeDTO2 productSizeDTO) {
        ProductSize productSize = modelMapper.map(productSizeDTO, ProductSize.class);
        productSize.setActive((byte) 1);
        return productSizeRespository.save(productSize);
    }

    public ProductSize update(ProductSizeDTO2 productSizeDTO) {
        ProductSize productSize = modelMapper.map(productSizeDTO, ProductSize.class);
    	productSize.setActive((byte) 1);
        return productSizeRespository.save(productSize);
    }

    public ProductSize delete(int id) {
        ProductSize productSize = this.getById(id);
        productSize.setActive((byte) 0);
        return productSizeRespository.save(productSize);
    }

    public ProductSize restore(int id) {
        ProductSize product = this.getById(id);
        product.setActive((byte) 1);
        return productSizeRespository.save(product);
    }

    public boolean existProductSizeById(int id) {
        return productSizeRespository.existsById(id);
    }

}
