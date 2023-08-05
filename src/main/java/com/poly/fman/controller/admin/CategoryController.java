package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.ProductTypeDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.service.ProductTypeService;
import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
public class CategoryController {

    private final ProductTypeService productTypeService;
    private final SessionService session;
    private final ParamService paramService;
        
    @GetMapping("/admin/categorys/")
    public String viewListCategory() {
        return "admin/layout/Category/category-list";
    }

    @GetMapping("/admin/categorys/list")
    @ResponseBody
    public ResponseEntity<List<ProductType>> getCategories() {
        return ResponseEntity.ok(productTypeService.getAllActive());
    }

    @GetMapping("/admin/categorys/{id}")
    @ResponseBody
    public ResponseEntity<ProductType> getCategory(@PathVariable("id") String id) {
        if (!productTypeService.existProductTypeById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productTypeService.getById(id));
    }

    @GetMapping("/admin/categorys/create")
    public String createForm() {
        return "admin/layout/Category/category-add";
    }

    @GetMapping("/admin/categorys/update-form")
    public String updateForm() {
        return "admin/layout/Category/category-update";
    }

    @PutMapping("/admin/categorys/{id}")
    public ResponseEntity<ProductTypeDTO> updateCategory(@PathVariable("id") String id, @RequestBody ProductTypeDTO productType){
        if(!productTypeService.existProductTypeById(id)){
            ResponseEntity.notFound().build();
        }
        productTypeService.update(productType);
        return ResponseEntity.ok(productType);
    }

    @DeleteMapping("/admin/categorys/delete/{id}")
    public ResponseEntity<ProductType> delete(@PathVariable("id") String id) {
        if (!productTypeService.existProductTypeById(id)) {
            return ResponseEntity.notFound().build();
        }
        productTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable("id") String id) {
        productTypeService.restore(id);
        session.set("isRestore", true);
        return "redirect:/admin/categorys/";
    }


    @PostMapping("/admin/categorys/create")
    public ResponseEntity<ProductTypeDTO> create(@RequestBody ProductTypeDTO productType) {
        if(productTypeService.existProductTypeById(productType.getId())){
            return ResponseEntity.badRequest().build();
        }
        productTypeService.create(productType);
        return ResponseEntity.ok(productType);
    }

    @PostMapping("/update/{id}")
    public String update(@Validated @ModelAttribute("productType") ProductTypeDTO productType, BindingResult result, @PathVariable("id") String id, Model model) {
        //Kiểm tra nếu lỗi sẽ quay lại trang tạo danh mục
        if (result.hasErrors()) {
            return "admin/layout/Category/category-update";
        } else {
            //Kiểm tra xem tên danh mục đã tồn tại hay chưa
            if (!(productTypeService.existProductTypeByName(productType.getName())) || (productType.getName().equalsIgnoreCase(productTypeService.getById(id).getName()))) {
//                Nếu thông tin hợp lệ thì tạo danh mục mới
//                Gửi biến để thông báo cho người dùng
                productTypeService.update(productType);
                session.set("isUpdate", true);

            } else {
                // Nếu Danh mục đã tồn tại thông báo cho người dùng
                model.addAttribute("isUpdateFalse", true);
                return "admin/layout/Category/category-update";
            }
        }
        return "redirect:/admin/categorys/update-form/" + productType.getId();
    }


}