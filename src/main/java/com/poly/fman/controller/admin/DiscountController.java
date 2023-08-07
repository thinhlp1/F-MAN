package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.ProductTypeDTO;
import com.poly.fman.dto.model.VoucherDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Voucher;
import com.poly.fman.service.VoucherService;
import com.poly.fman.service.common.DateUtils;

import com.poly.fman.service.common.SessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
public class DiscountController {
    private final VoucherService voucherService;

    private final SessionService session;

    @GetMapping("/admin/discounts/list")
    @ResponseBody
    public ResponseEntity<List<Voucher>> getListVoucher() {
        return ResponseEntity.ok(voucherService.getListVoucherActiveTrue());
    }

    @GetMapping("/admin/discounts/")
    public String getDiscounts() {
        return "admin/layout/Discount/discount_list";
    }

    @GetMapping("/admin/discounts/create")
    public String createForm() {
        return "admin/layout/Discount/discount_add";
    }

    @GetMapping("/admin/discounts/update-form")
    public String updateForm() {
        return "admin/layout/Discount/discount_update";
    }

    @GetMapping("/admin/discounts/{id}")
    @ResponseBody
    public ResponseEntity<Voucher> getCategory(@PathVariable("id") int id) {
        if (!voucherService.existVoucherById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(voucherService.getVoucherById(id));
    }

    @PostMapping("/admin/discounts/create")
    public ResponseEntity<VoucherDTO> create(@RequestBody @Valid VoucherDTO voucherDTO) {

        // Kiểm tra tên mã đã tồn tại
//        String checkVoucher = voucherService.voucherIsExisted(voucherDTO.getName());
//        if (checkVoucher != null) {
//            if (checkVoucher.equals("voucherIsExisted")) {
//                model.addAttribute("message", "Tên mã đã tồn tại");
//                return "admin/layout/Discount/discount_add";
//            }
//        }

//        if (result.hasErrors()) {
//            return "admin/layout/Discount/discount_add";
//        } else {
//            voucher = voucherService.create(voucherDTO);
//            model.addAttribute("voucher", voucher);
//        }
        if(voucherService.existVoucherByName(voucherDTO.getName())){
            return ResponseEntity.badRequest().build();
        }
        voucherService.create(voucherDTO);
        return ResponseEntity.ok(voucherDTO);
    }

    @PutMapping("/admin/discounts/{id}")
    public ResponseEntity<VoucherDTO> updateVoucher(@PathVariable("id") int id, @RequestBody @Valid VoucherDTO voucherDto){
        if(!voucherService.existVoucherById(id)){
            ResponseEntity.notFound().build();
        }
        voucherService.update(voucherDto, id);
        return ResponseEntity.ok(voucherDto);
    }

    @DeleteMapping("/admin/discounts/delete/{id}")
    public ResponseEntity<Voucher> delete(@PathVariable("id") int id) {
        if (!voucherService.existVoucherById(id)) {
            return ResponseEntity.notFound().build();
        }
        voucherService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/discounts/restore/{id}")
    public String restore(@PathVariable("id") int id) {
        voucherService.restore(id);
        session.set("isRestore", true);
        return "redirect:/admin/discounts/";
    }


}