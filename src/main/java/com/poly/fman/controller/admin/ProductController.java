package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.BrandDTO;
import com.poly.fman.dto.model.ProductDTO;
import com.poly.fman.dto.model.ProductDTO2;
import com.poly.fman.dto.model.ProductSizeCreateDTO;
import com.poly.fman.dto.model.ProductSizeDTO2;
import com.poly.fman.entity.Brand;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductHistory;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Size;

import com.poly.fman.service.BrandService;
import com.poly.fman.service.ProductHistoryService;
import com.poly.fman.service.ProductService;
import com.poly.fman.service.ProductSizeService;
import com.poly.fman.service.ProductTypeService;
import com.poly.fman.service.SizeService;
import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;


@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/admin/products")
public class ProductController {
	private ModelMapper modelMapper = new ModelMapper();
	private final ProductService productService;
	private final ProductSizeService productSizeService;
	private final SizeService sizeService;
	private final ParamService paramService;
	private final BrandService brandService;
	private final ProductTypeService productTypeService;
	private final SessionService sessionService;
	private final ProductHistoryService productHistoryService;

	@GetMapping("/")
	public String getProducts() {
		return "admin/layout/Product/product-list";
	}

	@GetMapping("/list") 
	@ResponseBody
	public ResponseEntity<List<Product>> getAllProduct() {
		return ResponseEntity.ok(productService.getAllActive());	
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
        if (!productService.existProductById(id)) {
            return ResponseEntity.notFound().build();
        }
		try {
			  productService.delete(id);
		} catch (Exception e) {
			System.out.println(e);
		}
      
        return ResponseEntity.ok().build();
    }
	

	  @GetMapping("/create")
   	  public String createFormProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
		// try {
		// 	 List<Brand> listBrand = new ArrayList<Brand>();
		//  listBrand = brandService.getAllActive();
		//  model.addAttribute("listBrands", listBrand);
		// } catch (Exception e) {
		// 	System.out.println(e);
		// }
		
        return "admin/layout/Product/product-add";
    	}

		
		@GetMapping("/create")
		@ResponseBody
	  public ResponseEntity<List<Brand>> getBrandsAll(){
		  return ResponseEntity.ok(brandService.getAllActive());       
	  }
	
}
