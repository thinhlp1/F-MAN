package com.poly.fman.service;

import java.util.List;

import org.modelmapper.ModelMapper;


import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.fman.dto.model.BrandDTO;
import com.poly.fman.dto.model.ProductTypeDTO;
import com.poly.fman.entity.Brand;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Size;
import com.poly.fman.repository.BrandRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
    private ModelMapper modelMapper = new ModelMapper();
    private final BrandRepository brandRepository;

    public List<Brand> getAll(){
        return brandRepository.findAll();
    }

    public Page<Brand> getAll(Pageable page){
        return brandRepository.findAll(page);
    }

    public Brand getById(String id){
        return brandRepository.findById(id).orElse(null);
    }
    public List<Brand> getAllActive() {
        return brandRepository.findAllByActiveIsTrue().orElse(null);
    }
    public Brand getByName(String name){
        return brandRepository.findByName(name).orElse(null);
    }

    public Brand create(BrandDTO brandDTO){
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        brand.setActive((byte) 1);
        return brandRepository.save(brand);
    }

    public Brand update(Brand brand){
      
        return brandRepository.save(brand);
    }


    

    public boolean delete(String id){
        try {
        	Brand brand = getById(id);
           if (brand != null) {
        	   byte Byte = 0;
        	   brand.setActive(Byte);
        	   brandRepository.save(brand);
           }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean existBrandById(String id) {
        return brandRepository.existsById(id);
    }
  
}
