package com.poly.fman.service;

import com.poly.fman.dto.model.SizeDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Size;
import com.poly.fman.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeService {
    private ModelMapper modelMapper = new ModelMapper();

    private final SizeRepository sizeRespository;

    public List<Size> getAll() {
        return sizeRespository.findAll();
    }

    public Page<Size> getAll(Pageable pageable) {
        return sizeRespository.findAll(pageable);
    }

    public List<Size> getAllActive() {
        return sizeRespository.findAllByActiveIsTrue().orElse(null);
    }

    public Page<Size> getAllActive(Pageable pageable) {
        return sizeRespository.findAllByActiveIsTrue(pageable).orElse(null);
    }

    // CHECK PARAM IS NUMBERIC
    public boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Page<Size> searchSizeByIdOrSize(String keyword, Pageable pageable) {
        if (!isNumeric(keyword)) {
            return sizeRespository.findByIdContainingIgnoreCaseAndActiveIsTrue(keyword, pageable);
        } else {
            return sizeRespository.findBySizeOrWidthOrLength(Float.parseFloat(keyword), pageable);
        }
    }

    public Page<Size> getAllActiveIsFalse(Pageable pageable) {
        return sizeRespository.findByActiveFalse(pageable);
    }

    public Size getById(String id) {
        return sizeRespository.findById(id).orElse(null);
    }

    public Size getBySize(Float size) {
        return sizeRespository.findBySize(size).orElse(null);
    }

    public Size create(SizeDTO sizeDTO) {
        Size size = modelMapper.map(sizeDTO, Size.class);
        size.setActive((byte) 1);
        return sizeRespository.save(size);
    }

    public Size update(SizeDTO sizeDTO) {
        Size size = modelMapper.map(sizeDTO, Size.class);
        return sizeRespository.save(size);
    }

    public Size delete(String id) {
        Size size = this.getById(id);
        // size.setId(size.getId() +Math.floor(Math.random() * 100));
        size.setActive((byte) 0);
        return sizeRespository.save(size);
    }

    public Size restore(String id) {
        Size size = this.getById(id);
        size.setActive((byte) 1);
        return sizeRespository.save(size);
    }

    public boolean existId(String id) {
        return sizeRespository.existsById(id);
    }

    public boolean existSize(Float size) {
        return sizeRespository.existsBySize(size);
    }
}
