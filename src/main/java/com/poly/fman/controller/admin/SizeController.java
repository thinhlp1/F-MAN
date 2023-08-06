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
        return ResponseEntity.ok(sizeService.getAllActive());
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

    @GetMapping("/update-form/{id}")
    public String updateForm(Model model, @PathVariable("id") String sizeId) {
        //Khi id được truyền qua lặp tức tìm kiếm theo ID và fill lên form
        Size size = sizeService.getById(sizeId);
        model.addAttribute("size", size);
        //Sau khi tạo mới một size sẽ hiển thị cho người dùng thông báo tạo thành công
        if (session.get("isCreate") != null) {
            model.addAttribute("isCreate", true);
            session.remove("isCreate");
        }
        if (session.get("isUpdate") != null) {
            model.addAttribute("isUpdate", true);
            session.remove("isUpdate");
        }
        return "admin/layout/Size/size_update";
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

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable("id") String id) {
        //Restore lại những size đã bị INACTIVE
        sizeService.restore(id);
        session.set("isRestore", true);
        return "redirect:/admin/sizes/";
    }


    @PostMapping("/update-form/{id}")
    public String update(@Validated @ModelAttribute("size") SizeDTO size, BindingResult result, Model model) {
        //Kiểm tra nếu lỗi sẽ quay lại trang tạo size
        if (result.hasErrors()) {
            return "admin/layout/Size/size_update";
        } else {
            //Kiểm tra xem size đã tồn tại hay chưa
            System.out.println("Size cữ: " + sizeService.getById(size.getId()).getSize());
            System.out.println("Size mới: " + size.getSize());
            if ((size.getSize().floatValue() == sizeService.getById(size.getId()).getSize().floatValue()) || !(sizeService.existSize(size.getSize()))) {
                //Lấy một cục đối tượng bên form và tiến hành setAcvite bằng true
                //Cập nhật lại một cục đối tượng đó
                size.setActive((byte) 1);
                sizeService.update(size);
                session.set("isUpdate", true);
            } else {
                // Nếu Size đã tồn tại thông báo cho người dùng
                model.addAttribute("isUpdateFalse", true);
                return "admin/layout/Size/size_update";
            }
        }
        return "redirect:/admin/sizes/update-form/" + size.getId();
    }


}