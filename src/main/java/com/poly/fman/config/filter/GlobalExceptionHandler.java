package com.poly.fman.config.filter;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(AccessDeniedException.class)
    // public Object handleAccessDeniedException(AccessDeniedException ex,
    // WebRequest request) {
    // if (request instanceof ServletWebRequest) {
    // System.out.println("URL");
    // Nếu truy cập thông qua trình duyệt
    // return "redirect:/auth/account";
    // } else {
    // System.out.println("API");
    // Nếu truy cập thông qua API
    // HttpServletResponse response = ((ServletWebRequest) request).getResponse();
    // response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    // return "Bạn không có quyền truy cập tài nguyên này.";
    // }
    // }

    // @ExceptionHandler(NoHandlerFoundException.class)
    // @ResponseStatus(code = HttpStatus.NOT_FOUND)
    // public Object pageNotFound(NoHandlerFoundException ex, HttpServletRequest
    // request, HttpServletResponse response) {
    // System.out.println("HẦ");
    // if (isApiRequest(request)) {
    // System.out.println("URL");
    // Nếu truy cập thông qua trình duyệt
    // return "redirect:/error/access-denies";
    // } else {
    // System.out.println("API");
    // response.setContentType("application/json");
    // response.setCharacterEncoding("UTF-8");
    // return "{\"error\": \"Not Found\"}";
    // }
    // }

    // private boolean isApiRequest(HttpServletRequest request) {

    // String contentTypeHeader = request.getHeader("Content-Type");
    // if (contentTypeHeader == null) {
    // return false;
    // }
    // return contentTypeHeader.contains("application");
    // }

    // Các phương thức xử lý ngoại lệ khác nếu cần thiết
}
