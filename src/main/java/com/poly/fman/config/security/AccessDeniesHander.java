package com.poly.fman.config.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccessDeniesHander implements AccessDeniedHandler {

    @Override
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (!isApiRequest(request)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect("/error/access-denies");
        } else {
            System.out.println("API");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Access Denied\"}");
        }
    }

    private boolean isApiRequest(HttpServletRequest request) {
        // Lấy giá trị của header "Content-Type"
        String contentTypeHeader = request.getHeader("Content-Type");

        // Kiểm tra nếu header "Content-Type" chứa "application/json" (có thể thay đổi
        // tùy theo API của bạn)
        // Nếu là null, giả định là yêu cầu từ trình duyệt
        if (contentTypeHeader == null) {
            return false;
        }
        return contentTypeHeader.contains("application");
    }

}
