package com.poly.fman.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

import com.poly.fman.service.common.CommonUtils;
import com.poly.fman.service.common.DateUtils;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO implements ModelDTO {

    private int id;

    @NotNull(message = "{NotNull.voucher.minPrice}")
    @Min(value = 0, message = "{Min.voucher.minPrice}")
    @Max(value = 2000000000, message = "{Max.voucher.minPrice}")
    private BigInteger minPrice;

    @NotNull(message = "{NotNull.voucher.endAt}")
    @Future(message = "{Future.voucher.endAt}")
    private LocalDateTime endAt;

    @NotBlank(message = "{NotBlank.voucher.name}")
    private String name;

    @NotNull(message = "{NotNull.voucher.salePercent}")
    @Min(value = 1, message = "{Min.voucher.salePercent}")
    @Max(value = 100, message = "{Max.voucher.salePercent}")
    private Float salePercent;

    // @PastOrPresent(message = "{PastOrPresent.voucher.startAt}")
    @NotNull(message = "{NotNull.voucher.startAt}")
    @FutureOrPresent(message = "{PresentOrFuture.voucher.startAt}")
    private LocalDateTime startAt;

    private Date updateAt;

    private Date createAt;

    public String getMinPriceStringVND() {
        return CommonUtils.convertToCurrencyString(minPrice, " VNĐ");
    }

    public String getTotalString() {
        return CommonUtils.convertToCurrencyString(minPrice, "");
    }

    public String getCreateAtString() {
        return DateUtils.toString(createAt, "dd/MM/yyyy HH:mm:ss");
    }
}
