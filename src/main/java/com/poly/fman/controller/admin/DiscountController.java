package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.VoucherDTO;
import com.poly.fman.entity.Voucher;
import com.poly.fman.service.VoucherService;
import com.poly.fman.service.common.DateUtils;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/discounts")
public class DiscountController {

    private final VoucherService voucherService;

    @ModelAttribute("items")
    public Page<Voucher> getListVoucher(
            @RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field,
            @RequestParam("state") Optional<String> state,
            Model model) {
        // Khởi tạo một đối tượng page
        Page<Voucher> page;
        // Và biến pageSize để render số lượng element trong 1 page
        int pageSize = 5;
        // Kiểm tra điều kiện nếu người dùng lọc theo những voucher không active
        Pageable pageable = PageRequest.of(p.orElse(0), pageSize, Sort.by(field.orElse("createAt")).descending());
        if (state.equals("upcommingVoucher")) {
            Date currentDate = new Date(); // Lấy giá trị ngày hiện tại
            DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00");
            System.out.println("====Ngày hiện tại là: " + DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00"));
            page = voucherService.getUpcomingVouchers(currentDate, pageable);
        } else if (state.equals("outDatedVoucher")) {// Lọc ra các voucher hết hạn (endAt <= currentDate)
            Date currentDate = new Date(); // Lấy giá trị ngày hiện tại
            DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00");
            System.out.println("====Ngày hiện tại là: " + DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00"));
            page = voucherService.getExpiredVouchers(currentDate, pageable);
        }
        page = voucherService.getListVoucher(pageable);
        model.addAttribute("field", field.orElse("createAt"));
        model.addAttribute("state", state.orElse("")); // Bổ sung tham số state vào model
        return page;
    }

    @GetMapping("/")
    public String getDiscounts(@RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field,
            @RequestParam("state") Optional<String> state, Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(p.orElse(0), pageSize,
                Sort.by(Sort.Direction.DESC, field.orElse("createAt")));
        Page<Voucher> items = voucherService.getListVoucher(pageable);

        model.addAttribute("items", items);
        model.addAttribute("state", state.orElse(""));
        return "admin/layout/Discount/discount_list";
    }

    @PostMapping("/")
    public String getDiscountsFilterAndSort(@RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field,
            @RequestParam("state") String state, Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(p.orElse(0), pageSize,
                Sort.by(Sort.Direction.DESC, field.orElse("createAt")));
        Page<Voucher> items = null;
        if (state.contains("upcommingVoucher")) {
            Date currentDate = new Date(); // Lấy giá trị ngày hiện tại
            DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00");
            items = voucherService.getUpcomingVouchers(currentDate, pageable);
        } else if (state.contains("outDatedVoucher")) {// Lọc ra các voucher hết hạn (endAt <= currentDate)
            Date currentDate = new Date(); // Lấy giá trị ngày hiện tại
            DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00");
            items = voucherService.getExpiredVouchers(currentDate, pageable);
        }

        model.addAttribute("items", items);
        return "admin/layout/Discount/discount_list";
    }

    @PostMapping("/filter")
    public String getDiscountsFilter(@RequestParam(value = "state", defaultValue = "presentVoucher") String state,
            @RequestParam(value = "p") Optional<Integer> p,
            @RequestParam(value = "field") Optional<String> field,
            Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(p.orElse(0), pageSize,
                Sort.by(Sort.Direction.DESC, field.orElse("createAt")));

        Page<Voucher> items = null;
        // Xử lý logic của bộ lọc dựa trên giá trị của state (upcommingVoucher hoặc
        // outDatedVoucher)
        if (state.equals("upcommingVoucher")) {
            Date currentDate = new Date(); // Lấy giá trị ngày hiện tại
            DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00");
            System.out.println("====Ngày hiện tại là: " + DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00"));
            items = voucherService.getUpcomingVouchers(currentDate, pageable);
        } else if (state.equals("outDatedVoucher")) {// Lọc ra các voucher hết hạn (endAt <= currentDate)
            Date currentDate = new Date(); // Lấy giá trị ngày hiện tại
            DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00");
            System.out.println("====Ngày hiện tại là: " + DateUtils.toString(currentDate, "yyyy-MM-dd HH:mm" + ":00"));
            items = voucherService.getExpiredVouchers(currentDate, pageable);
        }

        // Thực hiện các xử lý khác và trả về kết quả
        model.addAttribute("items", items);
        model.addAttribute("field", field.orElse("createAt"));
        model.addAttribute("state", state);
        return "admin/layout/Discount/discount_list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        Voucher voucher = new Voucher();
        model.addAttribute("voucher", voucher);
        return "admin/layout/Discount/discount_add";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(@PathVariable("id") int id, Model model) {
//        Voucher voucher = voucherService.getVoucher(id);
//        System.out.println("=======> Đây là id của voucher: " + voucher.getId());
//        model.addAttribute("voucher", voucher);
        return "admin/layout/Discount/discount_update";
    }

    @GetMapping("/details/{id}")
    public String details() {
        return "admin/layout/Discount/discount_details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        voucherService.delete(id);
        return "redirect:/admin/discounts/";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("voucher") VoucherDTO voucherDTO, BindingResult result,
            Model model) {
        Voucher voucher = new Voucher();

        // Kiểm tra tên mã đã tồn tại
        String checkVoucher = voucherService.voucherIsExisted(voucherDTO.getName());
        if (checkVoucher != null) {
            if (checkVoucher.equals("voucherIsExisted")) {
                model.addAttribute("message", "Tên mã đã tồn tại");
                return "admin/layout/Discount/discount_add";
            }
        }

        if (result.hasErrors()) {
            return "admin/layout/Discount/discount_add";
        } else {
            voucher = voucherService.create(voucherDTO);
            model.addAttribute("voucher", voucher);
        }
        return "redirect:/admin/discounts/update-form/" + voucher.getId();
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @Validated @ModelAttribute("voucher") VoucherDTO voucherDTO,
            BindingResult result, Model model) {

        // Kiểm tra tên mã đã tồn tại
        String checkVoucher = voucherService.voucherIsExisted(voucherDTO.getName());
        if (checkVoucher != null) {
            if (checkVoucher.equals("voucherIsExisted")) {
                model.addAttribute("message", "Tên mã đã tồn tại");
                return "admin/layout/Discount/discount_update";
            }
        }

        if (result.hasErrors()) {
            return "admin/layout/Discount/discount_update";
        }
        Voucher voucher = voucherService.update(voucherDTO, id);
        model.addAttribute("voucher", voucher);

        return "redirect:/admin/discounts/update-form/" + voucher.getId();
    }
}