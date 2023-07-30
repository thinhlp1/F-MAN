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
            model.addAttribute("err_code", statusCode);
            model.addAttribute("err_message", "Không có quyền truy cập");
            model.addAttribute("err_description", "Bạn không thể truy cập được trang này vui lòng liên hệ với nhà cung cấp!");
            return "user/view/error/error_page";
        } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("err_code", statusCode);
            model.addAttribute("err_message", "Trang này không tồn tại");
            model.addAttribute("err_description", "Trang bạn tìm kiếm không tồn tại, vui lòng kiểm tra lại đường dẫn và thử lại");
            return "user/view/error/error_page";
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute("err_code", statusCode);
            model.addAttribute("err_message", "Hệ thống đang gặp lỗi");
            model.addAttribute("err_description", "Hiện tại hệ thống đang gặp lỗi vui lòng thử lại trong vài phút");

            return "user/view/error/error_page";
        } else {
            model.addAttribute("err_code", statusCode);
            model.addAttribute("err_message", "Máy chủ đang bảo trì");
            model.addAttribute("err_description", "Máy chủ đang bảo trì để nâng cấp hệ thống, Vui lòng thử lại sau");
            return "user/view/error/error_page";
        }


    }

}
