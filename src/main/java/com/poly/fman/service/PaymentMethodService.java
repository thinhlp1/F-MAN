package com.poly.fman.service;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.fman.dto.model.PaymentMethodDTO;
import com.poly.fman.entity.PaymentMethod;
import com.poly.fman.repository.PaymentMethodRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentMethodService {
    private ModelMapper modelMapper;
    private final PaymentMethodRepository paymentRepositry;

    {
        new ModelMapper();
    }

    public PaymentMethod getPayment(String id) {
        PaymentMethod payment = paymentRepositry.findByIdAndActiveIsTrue(id).orElse(null);
        return payment;
    }

    public PaymentMethodDTO getPaymentDTO(String id) {
        PaymentMethod payment = paymentRepositry.findByIdAndActiveIsTrue(id).orElse(null);
        PaymentMethodDTO paymentDTO = modelMapper.map(payment, PaymentMethodDTO.class);
        return paymentDTO;
    }

    public Page<PaymentMethod> getListPayment(Pageable pageable) {
        return paymentRepositry.findAllByActiveIsTrue(pageable).orElse(null);
    }

    public String paymentIsExisted(String id, String name, String account_number) {
        PaymentMethod payment;
        payment = this.getPayment(id);
        // if(payment == null) {
        //     payment = this.paymentRepositry.findByName(name).orElse(null);
        //     if(payment == null){
        //         payment = this.paymentRepositry.findPaymentAccountNumber(account_number,(byte) 1);
        //     }
        // }
        if (payment != null) {
            if (payment.getId().equalsIgnoreCase(id)) {
                return "paymentIdIsExisted";
            } 
            // else if(payment.getName().equalsIgnoreCase(name)){
            //     return "paymentNameIsExisted";
            // }else if(payment.getAccount_number().equalsIgnoreCase(account_number)){
            //     return "paymentAccountNumberIsExisted";
            // }
        }
        return null;
    }

    public PaymentMethod create(PaymentMethodDTO paymentDTO) {
        PaymentMethod payment = modelMapper.map(paymentDTO, PaymentMethod.class);
        return paymentRepositry.save(payment);
    }

    public PaymentMethod update(PaymentMethodDTO paymentDTO, String id) {
        PaymentMethod payment = this.getPayment(id);
        payment.setName(paymentDTO.getName());
        payment.setImage(paymentDTO.getImage());
        payment.setAccount_number(paymentDTO.getAccount_number());
        payment.setActive(paymentDTO.getActive());
        return paymentRepositry.save(payment);
    }

    public PaymentMethod delete(String id) {
        PaymentMethod payment = this.getPayment(id);
        payment.setActive((byte) 0);
        return paymentRepositry.save(payment);
    }
}
