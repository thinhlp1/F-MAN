package com.poly.fman.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.fman.dto.analysis.DashBoardOverviewDTO;
import com.poly.fman.dto.analysis.DataDTO;
import com.poly.fman.entity.Product;
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
        DashBoardOverviewDTO dashBoardOverviewDTO = analysisService.analysisDashBoardOverViewData();
 
        model.addAttribute("dashBoardOverview", dashBoardOverviewDTO);
        return "admin/dashboard";
    }

}
