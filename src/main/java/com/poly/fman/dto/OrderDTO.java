package com.poly.fman.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.poly.fman.entity.Address;
import com.poly.fman.entity.PaymentMethod;
import com.poly.fman.entity.User;
import com.poly.fman.entity.Voucher;
import com.poly.fman.service.common.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements ModelDTO {
    
	private int id;

	private Date createAt;

	private String stateId;

	private BigInteger total;

	private Date updateAt;

    private String userId;

    private Integer addressId;

    private String paymentId;

    private Integer voucherId;

	private List<OrderItemDTO> listOrderItemDTO;

	private User user;

	private Address address;

	private PaymentMethod paymentMethod;

	private Voucher voucher;

	public String getCreateAtString(){
		return DateUtils.toString(createAt, "dd/mm/yyyy HH:mm");
	}
}
