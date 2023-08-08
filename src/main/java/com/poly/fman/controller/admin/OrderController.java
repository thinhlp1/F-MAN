package com.poly.fman.controller.admin;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.fman.dto.model.OrderDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderItem;
import com.poly.fman.entity.OrderState;
import com.poly.fman.entity.ProductType;
import com.poly.fman.repository.OrderStateRepository;
import com.poly.fman.service.OrderService;
import com.poly.fman.service.common.CommonUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final OrderStateRepository orderStateRepository;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String viewListOrder() {
        return "admin/layout/Order/order-list";
    }

     @GetMapping("/approve")
    public String viewApproveOrder() {
        return "admin/layout/Order/order-approve";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> listOrder = orderService.getAllOders();

        return ResponseEntity.ok(listOrder);
    }

    @GetMapping("/approve-list")
    @ResponseBody
    public ResponseEntity<List<OrderDTO>> getOrdersApprove() {

        List<OrderDTO> listOrder = orderService.getApproveOrder();

        return ResponseEntity.ok(listOrder);
    }

    @GetMapping("/view/details/{orderId}")
    public String getOrderTemplate(@PathVariable("orderId") Integer orderId) {

        return "admin/layout/Order/order-details";
    }

    @GetMapping("/details/{orderId}")
    @ResponseBody
    public ResponseEntity<ResponseDTO> getOrder(@PathVariable("orderId") Integer orderId) {
        OrderDTO orderDTO = orderService.getOrderDTO(orderId);
        if (orderDTO == null) {
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Không thể xem chi tiết đơn hàng này"));

        } else {
            return ResponseEntity.ok(orderDTO);

        }
    }

    @PostMapping("/approve/{orderId}")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> approveOrder(@PathVariable("orderId") Integer orderId, Model model) {

        boolean rs = orderService.approveOrder(orderId);

        if (rs) {
            return ResponseEntity.ok(new SimpleReponseDTO("200", "Đơn hàng đã được duyệt!"));
        } else {
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Đơn hàng duyệt không thành công!"));
        }

    }

    @PostMapping("/cancel/{orderId}")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> cancelOrder(@PathVariable("orderId") Integer orderId,
            @RequestBody String note, Model model) {
        boolean rs = orderService.cancelOrder(orderId, note);

        if (rs) {
            return ResponseEntity.ok(new SimpleReponseDTO("200", "Cancel success!"));
        } else {
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Cancel Failed!"));
        }

    }

}