package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.ProductTypeDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.service.ProductTypeService;
import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
        return ResponseEntity.ok(productTypeService.getAll());
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

    @PostMapping("/admin/categorys/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid ProductTypeDTO productType) {
        if (productTypeService.existProductTypeById(productType.getId())) {
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Danh mục này đã tồn tại"));
        }
        productTypeService.create(productType);
        return ResponseEntity.ok(productType);
    }

    @GetMapping("/admin/categorys/update-form")
    public String updateForm() {
        return "admin/layout/Category/category-update";
    }

    @PutMapping("/admin/categorys/{id}")
    public ResponseEntity<ResponseDTO> updateCategory(@PathVariable("id") String id, @Valid @RequestBody ProductTypeDTO productType) {
        if (productTypeService.existProductTypeById(id)) {
            productTypeService.update(productType);
        }
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

    @PutMapping("/admin/categorys/restore/{id}")
    public ResponseEntity<ProductType> restore(@PathVariable("id") String id) {
        if (!productTypeService.existProductTypeById(id)) {
            return ResponseEntity.notFound().build();
        }
        productTypeService.restore(id);
        return ResponseEntity.ok().build();
    }


}