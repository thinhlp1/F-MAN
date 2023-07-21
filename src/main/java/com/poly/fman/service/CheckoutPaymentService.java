package com.poly.fman.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.poly.fman.config.payment.Config;
import com.poly.fman.dto.model.TransactionDTO;
import com.poly.fman.dto.payment.PaymentReponse;
import com.poly.fman.dto.payment.PaymentRquest;
import com.poly.fman.entity.Bank;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.Transaction;
import com.poly.fman.repository.BankRepository;
import com.poly.fman.repository.OrderStateRepository;
import com.poly.fman.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckoutPaymentService {
    private final TransactionRepository transactionRepository;
    private final BankRepository bankRepository;
    private final OrderService orderService;
    private final OrderStateRepository orderStateRepository;

     public PaymentReponse createPaymentVnpay(PaymentRquest paymentRequestDTO, Long amt) throws UnsupportedEncodingException {
     
        // String orderType = req.getParameter("ordertype");
        long amount =amt * 100 ;
        String bankCode =paymentRequestDTO.getBankCode();

        System.out.println(amount);
        System.out.println(bankCode);

        String vnp_TxnRef = Config.getRandomNumber(8);
        // String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;
        
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", Config.vnp_Version);
        vnp_Params.put("vnp_Command", Config.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        // vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", Config.vnp_Locale);
        
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        // vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        PaymentReponse paymentReponseDTO = new PaymentReponse();
        paymentReponseDTO.setStatusCode("200");
        paymentReponseDTO.setMessage("Success");
        paymentReponseDTO.setUrl(paymentUrl);
        return paymentReponseDTO;
        // com.google.gson.JsonObject job = new JsonObject();
        // job.addProperty("code", "00");
        // job.addProperty("message", "success");
        // job.addProperty("data", paymentUrl);
        // Gson gson = new Gson();      
        // resp.getWriter().write(gson.toJson(job));
    }

    public Transaction createTransaction(TransactionDTO transactionDTO ){
        Bank bank = bankRepository.findById(transactionDTO.getBankCode()).orElse(null);
        Order order = orderService.getOrder(transactionDTO.getOrderId());
        order.setOrderState(orderStateRepository.findById("PENDING_APPROVAL").orElse(null));
        Transaction newTransaction = new Transaction();
        newTransaction.setBank(bank);
        newTransaction.setOrder(order);
        newTransaction.setPayDate(transactionDTO.getPayDate());
        newTransaction.setTransNo(transactionDTO.getTransNo());
        newTransaction.setBankNo(transactionDTO.getBankNo());
        return transactionRepository.save(newTransaction);
    }
}
