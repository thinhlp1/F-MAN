package com.poly.fman.controller.admin;

import java.util.List;

import com.poly.fman.dto.model.VoucherDTO;
import com.poly.fman.entity.Voucher;
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

    @GetMapping("/admin/payments/update-form")
    public String updateForm() {
        return "admin/layout/Payment/payment_update";
    }

    @GetMapping("/admin/payments/{id}")
    @ResponseBody
    public ResponseEntity<PaymentMethod> getCategory(@PathVariable("id") String id) {
        if (!paymentService.existPaymentById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentService.getPaymentById(id));
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
        try {
            //   Save file ảnh vào thư mục images
            paramService.saveSpringBootUpdated(photoFile, "src\\main\\resources\\static\\admin-resouce\\plugins\\images");

            // Thêm phương thức thanh toán vào cơ sở dữ liệu
            PaymentMethodDTO paymentDto = new PaymentMethodDTO();
            paymentDto.setId(paymentId);
            paymentDto.setName(paymentName);
            paymentDto.setAccount_number(cardNumber);
            paymentDto.setImage(photoFile.getOriginalFilename());
            // Kiểm tra xem phương thức thanh toán đã tồn tại hay chưa
            if (!paymentService.existPaymentById(paymentId)) {
                ResponseEntity.notFound().build();
            }
            paymentService.create(paymentDto);

            // Trả về đối tượng phương thức thanh toán đã được thêm
            return ResponseEntity.ok(paymentDto);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @PutMapping("/admin/payments/{id}")
    public ResponseEntity<PaymentMethodDTO> updateVoucher(@PathVariable("id") String id,
                                                          @RequestParam(value = "payment_name", required = false) String paymentName,
                                                          @RequestParam(value = "card_number", required = false) String cardNumber,
                                                          @RequestParam(value = "photo_file", required = false) MultipartFile photoFile) {
        try {
            PaymentMethodDTO paymentDto = new PaymentMethodDTO();
            if (photoFile == null) {
                paymentDto.setImage(paymentService.getPaymentById(id).getImage());
            } else {
                paramService.saveSpringBootUpdated(photoFile, "src\\main\\resources\\static\\admin-resouce\\plugins\\images");
                paymentDto.setImage(photoFile.getOriginalFilename());
            }

            paymentDto.setId(id);
            paymentDto.setName(paymentName);
            paymentDto.setAccount_number(cardNumber);
            paymentService.update(paymentDto, id);
            return ResponseEntity.ok(paymentDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @DeleteMapping("/admin/payments/delete/{id}")
    public ResponseEntity<PaymentMethod> delete(@PathVariable("id") String id) {
        if (!paymentService.existPaymentById(id)) {
            return ResponseEntity.notFound().build();
        }
        paymentService.delete(id);
        return ResponseEntity.ok().build();
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

