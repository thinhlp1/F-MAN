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

import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderItem;
import com.poly.fman.entity.OrderState;
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

    @GetMapping("/all")
    public String getOrders(Model model,
            @RequestParam(required = false) String orderStateId,
            @RequestParam(defaultValue = "createAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

//        Page<Order> orderPage = orderService.getOders(orderStateId, sortBy, sortOrder, page - 1, size);
//        List<OrderState> orderStates = orderStateRepository.findAll();
//        List<Order> listOrder = orderPage.getContent();
//
//        if (orderStateId == null || orderStateId.equals("")) {
//            orderStateId = "ALL";
//        }
//
//        model.addAttribute("orders", orderPage.getContent());
//        model.addAttribute("currentPage", orderPage.getNumber());
//        model.addAttribute("totalPages", orderPage.getTotalPages());
//        model.addAttribute("orderStates", orderStates);
//        model.addAttribute("sortBy", sortBy);
//        model.addAttribute("orderStateId", orderStateId);
//        model.addAttribute("sortOrder", sortOrder);
//        model.addAttribute("size", size);

        return "admin/layout/Order/order-list";
    }

    @GetMapping("/approve")
    public String getOrdersApprove(Model model,
            @RequestParam(required = false) String orderStateId,
            @RequestParam(defaultValue = "createAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

//        Page<Order> orderPage = orderService.getOders(orderStateId, sortBy, sortOrder, page - 1, size);
//        List<OrderState> orderStates = orderStateRepository.findAll();
//        List<Order> listOrder = orderPage.getContent();
//
//        if (orderStateId == null || orderStateId.equals("")) {
//            orderStateId = "ALL";
//        }
//
//        model.addAttribute("orders", orderPage.getContent());
//        model.addAttribute("currentPage", orderPage.getNumber());
//        model.addAttribute("totalPages", orderPage.getTotalPages());
//        model.addAttribute("orderStates", orderStates);
//        model.addAttribute("sortBy", sortBy);
//        model.addAttribute("orderStateId", orderStateId);
//        model.addAttribute("sortOrder", sortOrder);
//        model.addAttribute("size", size);

        return "admin/layout/Order/order-approve";
    }

    @GetMapping("/details/{orderId}")
    public String getOrder(@PathVariable("orderId") Integer orderId, Model model) {
//        Order order = orderService.getOrder(orderId);
//
//        List<OrderItem> listOderItem = order.getOrderItems();
//        Long tempTotal = (long) 0;
//        Long discount = (long) 0;
//
//        for (OrderItem orderItem : listOderItem) {
//            tempTotal += orderItem.getProductPrice().intValue() * orderItem.getQuantity();
//        }
//
//        if (order.getVoucher() != null) {
//            discount = (long) (tempTotal * order.getVoucher().getSalePercent() / 100);
//        }
//        model.addAttribute("tempTotal", CommonUtils.convertToCurrencyString(tempTotal, " VNĐ"));
//        model.addAttribute("discount", CommonUtils.convertToCurrencyString(discount, " VNĐ"));
//        model.addAttribute("order", order);
        return "admin/layout/Order/order-details";
    }

    @PostMapping("/approve/{orderId}")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> approveOrder(@PathVariable("orderId") Integer orderId, Model model) {

        boolean rs = orderService.approveOrder(orderId);

        if (rs){
               return ResponseEntity.ok(new SimpleReponseDTO("200", "Approve success!"));
        }else{
               return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Approve Failed!"));
        }

     
    }

    @PostMapping("/cancel/{orderId}")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> cancelOrder(@PathVariable("orderId") Integer orderId,@RequestBody String note, Model model) {
        boolean rs = orderService.cancelOrder(orderId,note);

       if (rs){
               return ResponseEntity.ok(new SimpleReponseDTO("200", "Cancel success!"));
        }else{
               return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Cancel Failed!"));
        }

    }

}