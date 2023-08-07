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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<BrandDTO> create(@RequestParam("photo_file") MultipartFile photoFile,
                                                   @RequestParam("brand_id") String brandId,
                                                   @RequestParam("brand_name") String brandName
                                                   ) {
   try {
            //   Save file ảnh vào thư mục images
            paramService.saveSpringBootUpdated(photoFile, "src\\main\\resources\\static\\admin-resouce\\plugins\\images");

            // Thêm phương thức thanh toán vào cơ sở dữ liệu
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setId(brandId);
            brandDTO.setName(brandName);
         
            brandDTO.setImage(photoFile.getOriginalFilename());
            // Kiểm tra xem phương thức thanh toán đã tồn tại hay chưa
            if (!brandService.existBrandById(brandId)) {
                ResponseEntity.notFound().build();
            }
            brandService.create(brandDTO);

            // Trả về đối tượng phương thức thanh toán đã được thêm
            return ResponseEntity.ok(brandDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @GetMapping("/update-form")
    public String updateForm() {
        return "admin/layout/Brand/brand-update";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateCategory(@PathVariable("id") String id, 
                                                   @RequestParam("photo_file") MultipartFile photoFile,
                                                   @RequestParam("brand_name") String brandName) {
        if (!brandService.existBrandById(id)) {
            ResponseEntity.notFound().build();
        }

            //   Save file ảnh vào thư mục images
            paramService.saveSpringBootUpdated(photoFile, "src\\main\\resources\\static\\admin-resouce\\plugins\\images");

            // Thêm phương thức thanh toán vào cơ sở dữ liệu
            Brand brand = new Brand();
            brand = brandService.getById(id);
            brand.setName(brandName);
         
            brand.setImage(photoFile.getOriginalFilename());

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