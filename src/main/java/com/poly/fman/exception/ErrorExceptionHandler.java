package com.poly.fman.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ErrorExceptionHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public Object handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.valueOf(status.toString());
        if (statusCode == HttpStatus.FORBIDDEN.value()) {
             model.addAttribute("err_message", "Bạn không có quyền truy cập trang này");
            model.addAttribute("err_image", "not_found.gif");
           return "user/view/error/error_page";
        } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("err_message", "Trang này không tồn tại");
            model.addAttribute("err_image", "not_found.gif");
           return "user/view/error/error_page";
        }else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute("err_message", "Có lỗi gì rồi bro ơi");
            model.addAttribute("err_image", "something_wrong.gif");
           return "user/view/error/error_page";
        }else{
                model.addAttribute("err_message", "Có lỗi gì rồi bro ơi");
            model.addAttribute("err_image", "something_wrong.gif");
           return "user/view/error/error_page";
        }

      
    }

}
