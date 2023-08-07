package com.poly.fman.service;

import com.poly.fman.dto.model.ProductTypeDTO;
import com.poly.fman.dto.model.VoucherDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Voucher;
import com.poly.fman.repository.VoucherRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class VoucherService {
    private ModelMapper modelMapper;
    private VoucherRepository voucherRepository;
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";

    {
        new ModelMapper();
    }

    // public List<Voucher> getVouchers() {
    // List<Voucher> list = voucherRepository.findByActiveOrderByCreateAtDesc(true);
    // return list;
    // }

    public Voucher getVoucherById(int id) {
        Voucher voucher = voucherRepository.findById(id).get();
        return voucher;
    }

    public boolean existVoucherById(int id) {
        return voucherRepository.existsById(id);
    }

    public boolean existVoucherByName(String name) {
        return voucherRepository.existsByName(name);
    }

    public List<Voucher> getListVoucherActiveTrue() {
        List<Voucher> list = voucherRepository.findAllByActiveIsTrue();
        return list;
    }

    public Page<Voucher> getUpcomingVouchers(Date currentDate, Pageable pageable) {
        return voucherRepository.findByStartAtGreaterThanEqual(currentDate, pageable);
    }

    public Page<Voucher> getExpiredVouchers(Date currentDate, Pageable pageable) {
        return voucherRepository.findByEndAtLessThanEqual(currentDate, pageable);
    }

    public void save(Voucher voucher) {
        this.voucherRepository.save(voucher);
    }

    public String voucherIsExisted(String name) {
        Voucher voucher = this.voucherRepository.findByNameAndActiveIsTrue(name).orElse(null);
        if (voucher != null) {
            if (voucher.getName().equalsIgnoreCase(name)) {
                return "voucherIsExisted";
            }
        }
        return null;
    }

    public Voucher create(VoucherDTO voucherDTO) {
        // Chuyển đổi LocalDateTime sang Date
        LocalDateTime startAt = voucherDTO.getStartAt();
        LocalDateTime endAt = voucherDTO.getEndAt();

        DateTimeFormatter.ofPattern(DATE_FORMAT);
        Date startDate = Date.from(startAt.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endAt.atZone(ZoneId.systemDefault()).toInstant());

        Voucher voucher = modelMapper.map(voucherDTO, Voucher.class);
        voucher.setStartAt(startDate);
        voucher.setEndAt(endDate);
        voucher.setCreateAt(new Date());
        voucher.setName(voucher.getName().toUpperCase());
        voucher.setActive((byte) 1);
        return voucherRepository.save(voucher);
    }



    public Voucher update(VoucherDTO voucherDTO, int id) {
        // Chuyển đổi LocalDateTime sang Date
        LocalDateTime startAt = voucherDTO.getStartAt();
        LocalDateTime endAt = voucherDTO.getEndAt();

        DateTimeFormatter.ofPattern(DATE_FORMAT);
        Date startDate = Date.from(startAt.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endAt.atZone(ZoneId.systemDefault()).toInstant());
        Voucher voucher = this.getVoucherById(id);
        voucher.setName(voucherDTO.getName());
        voucher.setSalePercent(voucherDTO.getSalePercent());
        voucher.setMinPrice(voucherDTO.getMinPrice());
        voucher.setStartAt(startDate);
        voucher.setEndAt(endDate);
        voucher.setUpdateAt(new Date());
        return voucherRepository.save(voucher);
    }

//    public ProductType update(VoucherDTO voucherDto) {
//        ProductType productType = modelMapper.map(productTypeDTO, ProductType.class);
//        productType.setActive((byte) 1);
//        return productTypeRespository.save(productType);
//    }

    public Voucher delete(int id) {
        Voucher voucher = this.getVoucherById(id);
        voucher.setActive((byte)0);
        voucher.setDeleteAt(new Date());
        return voucherRepository.save(voucher);
    }

    public Voucher restore(int id) {
        Voucher voucher = this.getVoucherById(id);
        voucher.setActive((byte) 1);
        return voucherRepository.save(voucher);
    }
}
