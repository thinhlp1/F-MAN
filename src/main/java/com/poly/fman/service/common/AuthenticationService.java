package com.poly.fman.service.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.poly.fman.entity.User;

import lombok.AllArgsConstructor;

@Service
public class AuthenticationService {
    public boolean checkPermision(Integer userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        if (userId.equals(userDetails.getId())) {
            return true;
            // Truy cập dữ liệu người dùng
        } else {
            return false;
            // Người dùng không có quyền truy cập
        }
    }
}
