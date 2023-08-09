package com.poly.fman.controller.user;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.poly.fman.dto.model.OrderDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.model.TransactionDTO;
import com.poly.fman.dto.order.CheckoutOrderResponseDTO;
import com.poly.fman.dto.order.CheckoutRequestDTO;
import com.poly.fman.dto.order.ReCheckoutReponseDTO;
import com.poly.fman.dto.payment.CheckoutPaymentReponse;
import com.poly.fman.dto.payment.PaymentReponse;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderState;
import com.poly.fman.entity.Transaction;
import com.poly.fman.repository.OrderStateRepository;
import com.poly.fman.repository.TransactionRepository;
import com.poly.fman.service.CartService;
import com.poly.fman.service.CheckoutPaymentService;
import com.poly.fman.service.OrderService;
import com.poly.fman.service.common.AuthenticationService;
import com.poly.fman.service.common.DateUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/orders")
@RequiredArgsConstructor
public class UserOrderController {

    private final OrderService orderService;
    private final OrderStateRepository orderStateRepository;
    private final CheckoutPaymentService checkoutPaymentService;
    private final TransactionRepository transactionRepository;
    private final AuthenticationService authenticationService;

    @GetMapping("/by-user/{userId}")
    public String getOrdersByUser(Model model, @PathVariable("userId") Integer userId,
            @RequestParam(required = false) String orderStateId,
            @RequestParam(defaultValue = "createAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) throws AccessDeniedException {

        if (!authenticationService.checkPermision(userId)) {
            throw new AccessDeniedException("Access Denied");
        }

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

        System.out.println("OKEE");

        return "user/view/order/order_list";
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable("orderId") Integer orderId, Model model) {
        OrderDTO orderDTO = orderService.getOrderDTO(orderId);
        if (!authenticationService.checkPermision(orderDTO.getUser().getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        if (orderDTO.getPaymentMethod().getId().equals("VISA")) {

            TransactionDTO transactionDTO = orderService.getTransactionDTO(orderId);
            System.out.println(transactionDTO);
            model.addAttribute("transaction", transactionDTO);

        }
        model.addAttribute("order", orderDTO);

        return "user/view/order/order";
    }

    @PostMapping("/checkout")
    @ResponseBody
    public ResponseEntity<ResponseDTO> checkout(@RequestBody CheckoutRequestDTO checkoutDTO) {
        System.out.println(checkoutDTO.toString());
        ResponseDTO checkoutOrderResponseDTO = orderService.create(checkoutDTO);
        if (checkoutOrderResponseDTO instanceof SimpleReponseDTO) {

            return ResponseEntity.status(500).body(checkoutOrderResponseDTO);
        } else {

            return ResponseEntity.ok(checkoutOrderResponseDTO);
        }
    }

    @GetMapping("/recheckout/{orderId}")
    @ResponseBody
    public ResponseEntity<ResponseDTO> recheckout(@PathVariable("orderId") Integer orderId) {
        try {
            ReCheckoutReponseDTO recheckoutReponseDTO = orderService.getRecheckout(orderId);
            return ResponseEntity.ok(recheckoutReponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Có lỗi xảy ra"));
        }
    }

    @PostMapping("/recheckout/{orderId}")
    @ResponseBody
    public ResponseEntity<ResponseDTO> confirmRecheckout(@PathVariable("orderId") Integer orderId) {
        try {
            OrderDTO orderDTO = orderService.getOrderDTO(orderId);
            CheckoutPaymentReponse checkoutPaymentReponse = new CheckoutPaymentReponse();
            PaymentReponse paymentReponse = new PaymentReponse();
            try {
                if (orderDTO.getPaymentMethod().getId().equals("VISA")) { // should be check bankCode
                    paymentReponse = checkoutPaymentService.createPaymentVnpay("NCB",
                            orderDTO.getTotal(), orderDTO.getId());
                }

            } catch (UnsupportedEncodingException e) {
                return ResponseEntity.ok(new SimpleReponseDTO("500", "Không thể thanh toán hóa đơn này"));
            }
            if (paymentReponse != null) {
                checkoutPaymentReponse.setPaymentReponse(paymentReponse);
                checkoutPaymentReponse
                        .setOrderId(orderDTO.getId());

            } else {
                return ResponseEntity.ok(new SimpleReponseDTO("500", "Không thể thanh toán hóa đơn này"));

            }

            return ResponseEntity.ok(checkoutPaymentReponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Có lỗi xảy ra"));
        }
    }

    @PostMapping("/checkout-payment")
    @ResponseBody
    public ResponseEntity<ResponseDTO> checkoutPayment(@RequestBody CheckoutRequestDTO checkoutDTO) {

        ResponseDTO checkoutOrderResponseDTO = orderService.create(checkoutDTO);
        if (checkoutOrderResponseDTO instanceof SimpleReponseDTO) {

            return ResponseEntity.status(500).body(checkoutOrderResponseDTO);
        }

        CheckoutPaymentReponse checkoutPaymentReponse = new CheckoutPaymentReponse();
        PaymentReponse paymentReponse = new PaymentReponse();
        try {
            if (checkoutDTO.getBankCode() != null || !checkoutDTO.getBankCode().equals("")) {
                paymentReponse = checkoutPaymentService.createPaymentVnpay(checkoutDTO.getBankCode(),
                        ((CheckoutOrderResponseDTO) checkoutOrderResponseDTO).getOrder().getTotal(),
                        ((CheckoutOrderResponseDTO) checkoutOrderResponseDTO).getOrder().getId());
            }

        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.ok(new SimpleReponseDTO("500", "Không thể thanh toán hóa đơn này"));
        }
        if (paymentReponse != null) {
            checkoutPaymentReponse.setPaymentReponse(paymentReponse);
            checkoutPaymentReponse
                    .setOrderId(((CheckoutOrderResponseDTO) checkoutOrderResponseDTO).getOrder().getId());

        } else {
            return ResponseEntity.ok(new SimpleReponseDTO("500", "Không thể thanh toán hóa đơn này"));

        }

        return ResponseEntity.ok(checkoutPaymentReponse);
    }

    @GetMapping("/checkout-vnpay-result/{orderId}")
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
            @PathVariable("orderId") Integer orderId,
            Model model) {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBankCode(vnpBankCode);
        transactionDTO.setBankNo(vnpBankTranNo);
        transactionDTO.setOrderId(orderId);
        transactionDTO.setPayDate(DateUtils.toDate(vnpPayDate, "yyyyMMddHHmmss"));
        transactionDTO.setTransNo(vnpTransactionNo);
        System.out.println(DateUtils.toDate(vnpPayDate, "yyyyMMddHHmmss"));

        Transaction transaction = checkoutPaymentService.createTransaction(transactionDTO);
        orderService.updateRecheckout(orderId);

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
