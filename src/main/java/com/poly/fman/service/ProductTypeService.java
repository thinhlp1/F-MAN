package com.poly.fman.service;

import com.poly.fman.dto.model.ProductTypeDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private ModelMapper modelMapper = new ModelMapper();

    private final ProductTypeRepository productTypeRespository;

    public List<ProductType> getAll() {
        return productTypeRespository.findAll();
    }

    public Page<ProductType> getAll(Pageable pageable) {
        return productTypeRespository.findAll(pageable);
    }

    public List<ProductType> getAllActive() {
        return productTypeRespository.findAllByActiveIsTrue().orElse(null);
    }

    public Page<ProductType> getAllActive(Pageable pageable) {
        return productTypeRespository.findAllByActiveIsTrue(pageable).orElse(null);
    }

    public Page<ProductType> getAllActiveIsFalse(Pageable pageable) {
        return productTypeRespository.findByActiveFalse(pageable);
    }

    public Page<ProductType> searchProductTypeByIdOrName(String keyword,Pageable pageable) {
        return productTypeRespository.findByIdOrNameContainingIgnoreCase(keyword, pageable);
    }


    public ProductType getById(String id) {
        return productTypeRespository.findById(id).orElse(null);
    }

    public ProductType getByName(String name ) {
        return productTypeRespository.findByName(name).orElse(null);
    }

    public ProductType create(ProductTypeDTO productTypeDTO) {
        ProductType productType = modelMapper.map(productTypeDTO, ProductType.class);
        productType.setActive((byte) 1);
        return productTypeRespository.save(productType);
    }

    public ProductType update(ProductTypeDTO productTypeDTO) {
        ProductType productType = modelMapper.map(productTypeDTO, ProductType.class);
        productType.setActive((byte) 1);
        return productTypeRespository.save(productType);
    }

    public ProductType delete(String id) {
        ProductType productType = this.getById(id);
        productType.setActive((byte) 0);
        return productTypeRespository.save(productType);
    }

    public ProductType restore(String id) {
        ProductType productType = this.getById(id);
        productType.setActive((byte) 1);
        return productTypeRespository.save(productType);
    }

    public boolean existProductTypeById(String id) {
        return productTypeRespository.existsById(id);
    }

    public boolean existProductTypeByName(String name) {
        return productTypeRespository.existsByName(name);
    }
}
