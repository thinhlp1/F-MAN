package com.poly.fman.dto.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.poly.fman.entity.Address;
import com.poly.fman.entity.OrderState;
import com.poly.fman.entity.PaymentMethod;
import com.poly.fman.entity.User;
import com.poly.fman.entity.Voucher;
import com.poly.fman.service.common.CommonUtils;
import com.poly.fman.service.common.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements ModelDTO,ResponseDTO {

    private int id;

    private Date createAt;

    private OrderStateDTO orderState;

    private Long total;

    private Date updateAt;

    private List<OrderItemDTO> listOrderItemDTO;

    private UserDTO user;

    private AddressDTO address;

    private PaymentMethodDTO paymentMethod;

    private TransactionDTO transactionDTO;

    private VoucherDTO voucher;

    private Long discount;
    
    private Long tempTotal;

    private String note;

    public String getTotalStringVND() {
        return CommonUtils.convertToCurrencyString(total, " VNĐ");
    }

    public String getTempTotalStringVND() {
        return CommonUtils.convertToCurrencyString(tempTotal, " VNĐ");
    }

    public String getDiscountStringVND() {
        return CommonUtils.convertToCurrencyString(discount, " VNĐ");
    }

    public String getCreateAtString() {
        return DateUtils.toString(createAt, "dd/MM/yyyy HH:mm");
    }

    public String getUpdateAtString() {
        return DateUtils.toString(updateAt, "dd/MM/yyyy HH:mm");
    }
}
