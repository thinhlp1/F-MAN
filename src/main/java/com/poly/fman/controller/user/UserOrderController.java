package com.poly.fman.controller.user;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.fman.dto.model.TransactionDTO;
import com.poly.fman.dto.payment.CheckoutPaymentReponse;
import com.poly.fman.dto.payment.PaymentReponse;
import com.poly.fman.dto.payment.PaymentRquest;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.dto.request.CheckoutDTO;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderItem;
import com.poly.fman.entity.OrderState;
import com.poly.fman.entity.Transaction;
import com.poly.fman.entity.User;
import com.poly.fman.repository.OrderRepository;
import com.poly.fman.repository.OrderStateRepository;
import com.poly.fman.repository.TransactionRepository;
import com.poly.fman.service.CheckoutPaymentService;
import com.poly.fman.service.OrderService;
import com.poly.fman.service.UserService;
import com.poly.fman.service.common.CommonUtils;
import com.poly.fman.service.common.DateUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/orders")
@RequiredArgsConstructor
public class UserOrderController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final UserService userService;
    private final OrderStateRepository orderStateRepository;
    private final CheckoutPaymentService checkoutPaymentService;
    private final TransactionRepository transactionRepository;
    private final HttpSession httpSession;

    @GetMapping("/by-user/{userId}")
    public String getOrdersByUser(Model model, @PathVariable("userId") Integer userId,
            @RequestParam(required = false) String orderStateId,
            @RequestParam(defaultValue = "createAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Order> orderPage = orderService.getOrdersByUser(userId, orderStateId, sortBy, sortOrder, page - 1, size);
        List<OrderState> orderStates = orderStateRepository.findAll();
        List<Order> listOrder = orderPage.getContent();

        if (orderStateId == null || orderStateId.equals("")) {
            orderStateId = "ALL";
        }

        model.addAttribute("orders", orderPage.getContent());
        model.addAttribute("currentPage", orderPage.getNumber());
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("orderStates", orderStates);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("orderStateId", orderStateId);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("size", size);
        model.addAttribute("userId", userId);

        return "user/view/order/order_list";
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable("orderId") Integer orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        Transaction transaction = transactionRepository.findByOrderId(orderId);
        List<OrderItem> listOderItem = order.getOrderItems();
        Long tempTotal = (long) 0;
        Long discount = (long) 0;

        for (OrderItem orderItem : listOderItem) {
            tempTotal += orderItem.getProduct().getPrice().intValue() * orderItem.getQuantity();
        }

        if (order.getVoucher() != null) {
            discount = (long) (tempTotal * order.getVoucher().getSalePercent() / 100);
        }
        model.addAttribute("tempTotal", CommonUtils.convertToCurrencyString(tempTotal, " VNĐ"));
        model.addAttribute("discount", CommonUtils.convertToCurrencyString(discount, " VNĐ"));
        model.addAttribute("order", order);
        model.addAttribute("transaction", transaction);
        return "user/view/order/order";
    }

    @PostMapping("/checkout")
    @ResponseBody
    public ResponseEntity<Map> checkout(@RequestBody CheckoutDTO checkoutDTO,
            Model model) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        checkoutDTO.setUserId((int) httpSession.getAttribute("userId"));
        String is = checkoutDTO.getIsReCheckout();
        Map<String, Object> reponse = new HashMap<>();
        Order oder;
        if (checkoutDTO.getIsReCheckout().equals("true")) {
            oder = orderService.reCreate(checkoutDTO);
        } else {
            oder = orderService.create(checkoutDTO);
        }
        if (oder != null) {
            reponse.put("status", 200);
            reponse.put("message", "Checkout success");
            reponse.put("orderId", oder.getId());
        } else {
            reponse.put("status", 500);
            reponse.put("message", "Checkout failed");

        }

        return ResponseEntity.ok(reponse);
    }

    @PostMapping("/checkout-payment")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> checkoutPayment(@RequestBody CheckoutDTO checkoutDTO,
            @RequestParam(required = false) Model model) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        checkoutDTO.setUserId((int) httpSession.getAttribute("userId"));
        PaymentRquest paymentRequestDTO = checkoutDTO.getPaymentRequestDTO();
        String is = checkoutDTO.getIsBuyNow();
        Order oder;
        if (checkoutDTO.getIsReCheckout().equals("true")) {
            oder = orderService.reCreate(checkoutDTO);
        } else {
            oder = orderService.create(checkoutDTO);
        }

        CheckoutPaymentReponse checkoutPaymentReponse = new CheckoutPaymentReponse();
        PaymentReponse paymentReponse = new PaymentReponse();
        try {
            if (paymentRequestDTO.getBankCode().equals("NCB")) {
                paymentReponse = checkoutPaymentService.createPaymentVnpay(paymentRequestDTO, oder.getTotal().longValue());
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (oder != null) {
            checkoutPaymentReponse.setPaymentReponse(paymentReponse);
            checkoutPaymentReponse.setMessage("Checkout success");
            checkoutPaymentReponse.setStatusCode("200");
            checkoutPaymentReponse.setOrderId(oder.getId());
            httpSession.setAttribute("orderId", oder.getId());

        } else {
            return ResponseEntity.ok(new SimpleReponseDTO("200", "Can't create order"));

        }

        return ResponseEntity.ok(checkoutPaymentReponse);
    }

    @GetMapping("/checkout-vnpay-result")
    public String checkoutResult(@RequestParam(value = "vnp_Amount", required = false) String vnpAmount,
            @RequestParam(value = "vnp_BankCode", required = false) String vnpBankCode,
            @RequestParam(value = "vnp_BankTranNo", required = false) String vnpBankTranNo,
            @RequestParam(value = "vnp_CardType", required = false) String vnpCardType,
            @RequestParam(value = "vnp_OrderInfo", required = false) String vnpOrderInfo,
            @RequestParam(value = "vnp_PayDate", required = false) String vnpPayDate,
            @RequestParam(value = "vnp_ResponseCode", required = false) String vnpResponseCode,
            @RequestParam(value = "vnp_TmnCode", required = false) String vnpTmnCode,
            @RequestParam(value = "vnp_TransactionNo", required = false) String vnpTransactionNo,
            @RequestParam(value = "vnp_TransactionStatus", required = false) String vnpTransactionStatus,
            @RequestParam(value = "vnp_TxnRef", required = false) String vnpTxnRef,
            @RequestParam(value = "vnp_SecureHash", required = false) String vnpSecureHash,
            Model model) {

        Integer orderId = (Integer) httpSession.getAttribute("orderId");

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBankCode(vnpBankCode);
        transactionDTO.setBankNo(vnpBankTranNo);
        transactionDTO.setOrderId(orderId);
        transactionDTO.setPayDate(DateUtils.toDate(vnpPayDate, "yyyyMMddHHmmss"));
        transactionDTO.setTransNo(vnpTransactionNo);
        System.out.println(DateUtils.toDate(vnpPayDate, "yyyyMMddHHmmss"));

        Transaction transaction = checkoutPaymentService.createTransaction(transactionDTO);
        model.addAttribute("transaction", transaction);
        return "user/view/order/checkout_result";
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
