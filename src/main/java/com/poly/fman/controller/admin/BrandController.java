package com.poly.fman.controller.admin;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;
import com.poly.fman.dto.model.BrandDTO;
import com.poly.fman.entity.Brand;
import com.poly.fman.service.BrandService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/brands")
public class BrandController {

    private BrandService brandService;
    private final SessionService session;
    
    ParamService paramService ;
    @GetMapping("/")
    public String getBrands(Model model, @RequestParam("p") Optional<Integer> p, @RequestParam("field") Optional<String> field){
    	// sắp xếp 
    	Sort sort = Sort.by(Direction.DESC, field.orElse("name"));
		model.addAttribute("field", field.orElse("name"));
		
		// phân trang
		Pageable pageable = PageRequest.of(p.orElse(0), 2, sort);
		
		Page<Brand> page = brandService.getAll(pageable);
		
		model.addAttribute("page", page);
  	
        return "admin/layout/Brand/brand-list";
    }

    @GetMapping("/create")
		@PreAuthorize("hasRole('ADMIN')") 
    public String createForm(Model model){
    	BrandDTO brand = new BrandDTO();
    	model.addAttribute( "brand",brand);
        return "admin/layout/Brand/brand-add";
    }

    @PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')") 
    public String create(@Validated @ModelAttribute("brand") BrandDTO brand ,BindingResult result ,@RequestParam("photo_file") MultipartFile img, Model model){

    	if (result.hasErrors()) {
    		if (img.isEmpty()) {
        		model.addAttribute("message_img", "Vui lòng chọn hình ảnh");
       	}
    		 return "admin/layout/Brand/brand-add";
    	}
    	
    	Brand brandCheck = new Brand();
    	brandCheck = brandService.getById(brand.getId());
    	if(brandCheck != null) {
    		model.addAttribute("message_id", "ID đã tồn tại");
    		 return "admin/layout/Brand/brand-add";
    	}
    	
    	
    	if (!img.isEmpty()) {
			String image = paramService.save(img, "/views/admin/plugins/images/");
			brand.setImage(image);
		}
    	
    	byte Byte = 1;
    	brand.setActive(Byte);
    	brandService.create(brand);
        return "admin/layout/Brand/brand-add";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(Model model, @PathVariable("id") String brandID){
    	Brand brand =  brandService.getById(brandID);
    	byte Byte = brand.getActive();
    	
    	if (Byte == 1) {
    		model.addAttribute("activeBrand", true);
    	}else {
    		model.addAttribute("activeBrand", false);
    	}
    	model.addAttribute("brand", brand);
    	session.set("brandSs", brand);
        return "admin/layout/Brand/brand-update";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") String brandID){
    	
    	Brand brand =  brandService.getById(brandID);
    	
    	model.addAttribute("brand", brand);
        return "admin/layout/Brand/brand-details";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id){
    	brandService.delete(id);
        return "redirect:/admin/brands/";
    }


    @PostMapping("/update-form/{id}")
    public String update(Model model , @ModelAttribute("brand") BrandDTO brand ,@RequestParam("photo_file") MultipartFile img , @RequestParam("activeBrand") Optional <Boolean> activeBrand){
    	
    	
    	 boolean active = activeBrand.orElse(false);
    	 if (active == true) {
    		 brand.setActive((byte) 1);
    	 }else {
    		 brand.setActive((byte) 0);
    	 }
    	  if (!img.isEmpty()) {
    		  System.out.println("loi");
    			String image = paramService.save(img, "/views/admin/plugins/images/");
      			brand.setImage(image);
  		}else {
  			Brand brandSession = new Brand();
  			brandSession = session.get("brandSs");
  			brand.setImage(brandSession.getImage());
  		}
          brandService.update(brand);
        return "redirect:/admin/brands/update-form/" + brand.getId();
    }

    
}