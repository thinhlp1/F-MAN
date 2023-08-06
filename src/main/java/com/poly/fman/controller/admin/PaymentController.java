package com.poly.fman.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.poly.fman.dto.model.PaymentMethodDTO;
import com.poly.fman.entity.PaymentMethod;
import com.poly.fman.service.PaymentMethodService;
import com.poly.fman.service.common.ParamService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
public class PaymentController {

    private final ParamService paramService;
    private final PaymentMethodService paymentService;

    @GetMapping("/admin/payments/")
    public String getPayments() {
        return "admin/layout/Payment/payment_list";
    }

    @GetMapping("/admin/payments/list")
    @ResponseBody
    public ResponseEntity<List<PaymentMethod>> getListPayment() {
        return ResponseEntity.ok(paymentService.getListPayment());
    }

    @GetMapping("/admin/payments/create")
    public String createForm() {
        return "admin/layout/Payment/payment_add";
    }

//    @GetMapping("/update-form/{id}")
//    public String updateForm(@PathVariable("id") String id, Model model) {
////        PaymentMethodDTO paymentDTO = paymentService.getPaymentDTO(id);
////        model.addAttribute("payment", paymentDTO);
//        return "admin/layout/Payment/payment_update";
//    }

//    @GetMapping("/details/{id}")
//    public String details() {
//        return "admin/layout/Payment/payment_details";
//    }

//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable("id") String id) {
//        paymentService.delete(id);
//        return "redirect:/admin/payments/";
//    }


    @PostMapping("/admin/payments/create")
    public ResponseEntity<PaymentMethodDTO> create(@RequestParam("photo_file") MultipartFile photoFile,
                                                   @RequestParam("payment_id") String paymentId,
                                                   @RequestParam("payment_name") String paymentName,
                                                   @RequestParam("card_number") String cardNumber) {
        // Kiểm tra các lỗi null, định dạng, hình ảnh
//        if (result.hasErrors() || img.isEmpty()) {
//            if (img.isEmpty()) {
//                model.addAttribute("message_img", "Vui lòng chọn hình ảnh");
//            }
//            return "admin/layout/Payment/payment_add";
//        } else {
//            String image = paramService.save(img, "/views/admin/plugins/images/");
//            paymentDTO.setImage(image);
//            paymentService.create(paymentDTO);
//            model.addAttribute("payment", paymentDTO);
//        }
//            Save file ảnh vào thư mục images
            paramService.saveSpringBootUpdated(photoFile, "src\\main\\resources\\static\\admin-resouce\\plugins\\images");
//            // Thêm phương thức thanh toán vào cơ sở dữ liệu
            PaymentMethodDTO paymentDto = new PaymentMethodDTO();
            paymentDto.setId(paymentId);
            paymentDto.setName(paymentName);
            paymentDto.setAccount_number(cardNumber);
            paymentDto.setImage(photoFile.getOriginalFilename());
        // Kiểm tra xem phương thức thanh toán đã tồn tại hay chưa
//            if (paymentService.existPaymentById(paymentDTO.getId())) {
//                return ResponseEntity.badRequest().build();
//            }
            paymentService.create(paymentDto);

            // Trả về đối tượng phương thức thanh toán đã được thêm
            return ResponseEntity.ok(paymentDto);
    }
}

//    @PostMapping("/update/{id}")
//    public String update(@PathVariable("id") String id,
//            @Validated @ModelAttribute("payment") PaymentMethodDTO paymentDTO, BindingResult result, Model model,
//            @RequestParam("photo_file") MultipartFile img) {
//
//        if (img.isEmpty()) {
//            paymentDTO.setImage(paymentService.getPayment(id).getImage());
//        } else {
//            String image = paramService.save(img, "/views/admin/plugins/images/");
//            paymentDTO.setImage(image);
//        }
//
//        if (result.hasErrors()) {
//            return "admin/layout/Payment/payment_update";
//        }
//
//        PaymentMethod payment = paymentService.update(paymentDTO, id);
//        model.addAttribute("payment", payment);
//        return "redirect:/admin/payments/update-form/" + payment.getId();
//    }

