package com.poly.fman.service;

import com.poly.fman.entity.ProductHistory;
import com.poly.fman.repository.ProductHistoryRepository;
	import lombok.RequiredArgsConstructor;
	//import org.modelmapper.ModelMapper;
	import org.springframework.data.domain.Page;
	import org.springframework.data.domain.Pageable;
	import org.springframework.stereotype.Service;
	import java.util.List;
	@Service
	@RequiredArgsConstructor
	public class ProductHistoryService {
	  //  private ModelMapper modelMapper = new ModelMapper();

	    private final ProductHistoryRepository productHistoryRepository;

	    public List<ProductHistory> getAll() {
	        return productHistoryRepository.findAll();
	    }

	    public Page<ProductHistory> getAll(Pageable pageable) {
	        return productHistoryRepository.findAll(pageable);
	    }

	 

	    public ProductHistory getById(String id) {
	        return productHistoryRepository.findById(id).orElse(null);
	    }


	    public ProductHistory create(ProductHistory productHistory) {   
	        return productHistoryRepository.save(productHistory);
	    }

	    public ProductHistory update(ProductHistory productHistory) {
	        return productHistoryRepository.save(productHistory);
	    }

	  

	
	   
	  
	}


