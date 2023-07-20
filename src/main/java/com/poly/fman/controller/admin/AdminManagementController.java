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

    @GetMapping("/dashboard")
    public String index(Model model) {
//        DashBoardOverviewDTO dashBoardOverviewDTO = analysisService.analysisDashBoardOverViewData();
 
//        model.addAttribute("dashBoardOverview", dashBoardOverviewDTO);
        return "admin/layout/dashboard";
    }

    @GetMapping("/notfound")
    public String notFound() {
        return "admin/layout/404";
    }

}
