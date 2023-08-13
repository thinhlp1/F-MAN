package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.ProductTypeDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.model.SizeDTO;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Size;
import com.poly.fman.service.SizeService;
import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.Optional;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/admin/sizes")
public class SizeController {

    private final SizeService sizeService;
    private final SessionService session;
    private final ParamService paramService;

    @GetMapping("/")
    public String getSizes(Model model) {
        return "admin/layout/Size/size_list";
    }
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<Size>> getCategories() {
        return ResponseEntity.ok(sizeService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Size> getCategory(@PathVariable("id") String id) {
        if (!sizeService.existId(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sizeService.getById(id));
    }

    @GetMapping("/create")
    public String createForm() {
        return "admin/layout/Size/size_add";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid SizeDTO size) {
        if (sizeService.existId(size.getId())) {
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Danh mục này đã tồn tại"));
        }
        sizeService.create(size);
        return ResponseEntity.ok(size);
    }

    @GetMapping("/update-form")
    public String updateForm() {
        return "admin/layout/Size/size_update";
    }

    @PutMapping("/update-form/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable("id") String id,@Valid @RequestBody SizeDTO size) {
        if(sizeService.existId(id)) {
            sizeService.update(size);
        }
        return ResponseEntity.ok(size);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Size> delete(@PathVariable("id") String id) {
        if(!sizeService.existId(id)){
            ResponseEntity.notFound().build();
        }
        //Xóa mềm bằng cách update lại trạng thái cho ACTIVE
        sizeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Size> restore(@PathVariable("id") String id) {
        //Restore lại những size đã bị INACTIVE
        if(!sizeService.existId(id)){
            ResponseEntity.notFound().build();
        }
        sizeService.restore(id);
        return ResponseEntity.ok().build();
    }



}