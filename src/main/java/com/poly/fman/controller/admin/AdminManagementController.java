package com.poly.fman.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.fman.dto.analysis.DashBoardDataDTO;
import com.poly.fman.dto.auth.AdminComponentDataDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.entity.User;
import com.poly.fman.service.AnalysisService;
import com.poly.fman.service.OrderService;
import com.poly.fman.service.ProductService;
import com.poly.fman.service.UserService;
import com.poly.fman.service.common.CookieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminManagementController {

    private final AnalysisService analysisService;
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final CookieService cookieService;

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/layout/dashboard";
    }

    @GetMapping("/dashboard-data")
    @ResponseBody
    public ResponseEntity<DashBoardDataDTO> getDashboarData() {
        DashBoardDataDTO dashBoardDataDTO = analysisService.analysisDashBoardOverViewData();
        return ResponseEntity.ok(dashBoardDataDTO);
    }

    @GetMapping("/componet-data")
    @ResponseBody
    public ResponseEntity<ResponseDTO> getComponentData() {
        AdminComponentDataDTO adminComponentDataDTO = new AdminComponentDataDTO();
        adminComponentDataDTO.setOrderApprove(orderService.getOrderApproveQuantity());
        User user = userService.getUser(cookieService.getValue("username"));
        adminComponentDataDTO.setUsername(user.getUsername());
        adminComponentDataDTO.setImage(user.getImage());

        return ResponseEntity.ok(adminComponentDataDTO);
    }

}
