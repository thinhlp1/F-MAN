package com.poly.fman.controller.admin;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.poly.fman.dto.PaymentMethodDTO;
import com.poly.fman.entity.PaymentMethod;
import com.poly.fman.service.PaymentMethodService;
import com.poly.fman.service.common.ParamService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/payments")
public class PaymentController {

    private final ParamService paramService;
    private final PaymentMethodService paymentService;

    @ModelAttribute("items")
    public Page<PaymentMethod> getListVoucher(
            @RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field,
            Model model) {
        // Khởi tạo một đối tượng page
        Page<PaymentMethod> page;
        // Và biến pageSize để render số lượng element trong 1 page
        int pageSize = 5;
        // Kiểm tra điều kiện nếu người dùng lọc theo những voucher không active
        Pageable pageable = PageRequest.of(p.orElse(0), pageSize, Sort.by(field.orElse("id")).ascending());
        page = paymentService.getListPayment(pageable);
        model.addAttribute("field", field.orElse("id"));
        return page;
    }

    @GetMapping("/")
    public String getPayments() {
        return "admin/layout/Payment/payment_list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        PaymentMethodDTO paymentDTO = new PaymentMethodDTO();
        model.addAttribute("payment", paymentDTO);
        return "admin/layout/Payment/payment_add";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(@PathVariable("id") String id, Model model) {
//        PaymentMethodDTO paymentDTO = paymentService.getPaymentDTO(id);
//        model.addAttribute("payment", paymentDTO);
        return "admin/layout/Payment/payment_update";
    }

    @GetMapping("/details/{id}")
    public String details() {
        return "admin/layout/Payment/payment_details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        paymentService.delete(id);
        return "redirect:/admin/payments/";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("payment") PaymentMethodDTO paymentDTO, BindingResult result,
            @RequestParam("photo_file") MultipartFile img,
            Model model) {

            String checkPayment = this.paymentService.paymentIsExisted(paymentDTO.getId(), paymentDTO.getName(),
                    paymentDTO.getAccount_number());
            System.out.println("======Giá trị của checkPayment" + checkPayment);
            if (checkPayment != null) {
                if (checkPayment.equals("paymentIdIsExisted")) {
                    model.addAttribute("messageId", "ID phương thức thanh toán đã tồn tại");
                    return "admin/layout/Payment/payment_add";
                } 
                // else if (checkPayment.equals("paymentNameIsExisted")) {
                //     System.out.println("======Chạy vô tới đây");
                //     model.addAttribute("messageName", "Tên phương thức thanh toán đã tồn tại");
                //     return "admin/layout/Payment/payment_add";
                // } else if (checkPayment.equals("paymentAccountNumberIsExisted")) {
                //     model.addAttribute("messageAccountNumber", "Số tài khoản đã tồn tại");
                //     return "admin/layout/Payment/payment_add";
                // }
            }
        

        // Kiểm tra các lỗi null, định dạng, hình ảnh
        if (result.hasErrors() || img.isEmpty()) {
            if (img.isEmpty()) {
                model.addAttribute("message_img", "Vui lòng chọn hình ảnh");
            }
            return "admin/layout/Payment/payment_add";
        } else {
            String image = paramService.save(img, "/views/admin/plugins/images/");
            paymentDTO.setImage(image);
            paymentService.create(paymentDTO);
            model.addAttribute("payment", paymentDTO);
        }
        return "redirect:/admin/payments/update-form/" + paymentDTO.getId();
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id,
            @Validated @ModelAttribute("payment") PaymentMethodDTO paymentDTO, BindingResult result, Model model,
            @RequestParam("photo_file") MultipartFile img) {

        if (img.isEmpty()) {
            paymentDTO.setImage(paymentService.getPayment(id).getImage());
        } else {
            String image = paramService.save(img, "/views/admin/plugins/images/");
            paymentDTO.setImage(image);
        }

        if (result.hasErrors()) {
            return "admin/layout/Payment/payment_update";
        }

        PaymentMethod payment = paymentService.update(paymentDTO, id);
        model.addAttribute("payment", payment);
        return "redirect:/admin/payments/update-form/" + payment.getId();
    }

}