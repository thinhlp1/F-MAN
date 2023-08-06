package com.poly.fman.controller.admin;


import java.util.List;


import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;

import jakarta.validation.Valid;

import com.poly.fman.dto.model.BrandDTO;

import com.poly.fman.entity.Brand;

import com.poly.fman.service.BrandService;

import lombok.AllArgsConstructor;



@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/admin/brands")
public class BrandController {

    private BrandService brandService;
    private final SessionService session;
    
    ParamService paramService ;
    @GetMapping("/")
    public String getBrands(){
        return "admin/layout/Brand/brand-list";
    }

	  @GetMapping("/list")
	  @ResponseBody
    public ResponseEntity<List<Brand>> getBrandsAll(){
		return ResponseEntity.ok(brandService.getAllActive());       
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Brand> getBrand(@PathVariable("id") String id) {
        if (!brandService.existBrandById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brandService.getById(id));
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("brand", new BrandDTO());
        return "admin/layout/Brand/brand-add";
    }

    @PostMapping("/create")
    public ResponseEntity<BrandDTO> create(@RequestBody @Valid BrandDTO brand) {
        if (brandService.existBrandById(brand.getId())) {
            System.out.println("Tồn tại");
            return ResponseEntity.badRequest().build();
        }
        try {
              brandService.create(brand);
        } catch (Exception e) {
           System.out.println("lõi " + e);
        }
      
        return ResponseEntity.ok(brand);
    }

    @GetMapping("/update-form")
    public String updateForm() {
        return "admin/layout/Brand/brand-update";
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateCategory(@PathVariable("id") String id, @RequestBody BrandDTO brand) {
        if (!brandService.existBrandById(id)) {
            ResponseEntity.notFound().build();
        }
		brandService.update(brand);
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Brand> delete(@PathVariable("id") String id) {
        if (!brandService.existBrandById(id)) {
            return ResponseEntity.notFound().build();
        }
        brandService.delete(id);
        return ResponseEntity.ok().build();
    }

    // @GetMapping("/restore/{id}")
    // public String restore(@PathVariable("id") String id) {
    //     productTypeService.restore(id);
    //     session.set("isRestore", true);
    //     return "redirect:/admin/categorys/";
    // }

    
}