package com.poly.fman.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.fman.service.AnalysisService;
import com.poly.fman.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminManagementController {

    private final AnalysisService analysisService;
    private final ProductService productService;

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/layout/dashboard";
    }

}
