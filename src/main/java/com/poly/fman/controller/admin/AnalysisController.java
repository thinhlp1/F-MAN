package com.poly.fman.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.fman.dto.analysis.AnalysisProductDTO;
import com.poly.fman.dto.analysis.AnalysisRevenueDTO;
import com.poly.fman.dto.analysis.DashBoardDataDTO;
import com.poly.fman.dto.analysis.DataDTO;
import com.poly.fman.dto.analysis.DataSellProductDTO;
import com.poly.fman.service.AnalysisService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/analysis")
@RequiredArgsConstructor
public class AnalysisController {
    private final AnalysisService analysisService;

    @GetMapping("/date")
    public String analysisDate() {
        return "admin/layout/Analysis/analysis_date";
    }

    @GetMapping("/view/revenue")
    public String analysisRevenue() {
        return "admin/layout/Analysis/analysis_revenue";
    }

    @GetMapping("/view/order")
    public String analysisOrder() {
        return "admin/layout/Analysis/analysis_orders";
    }

      @GetMapping("/view/sellProducts")
    public String analysisSellProduct() {
        return "admin/layout/Analysis/analysis_sell_product";
    }

    @GetMapping("/revenue")
    @ResponseBody
    public ResponseEntity<AnalysisRevenueDTO> revenue(@RequestParam(required = false) Integer year, Model model) {

        AnalysisRevenueDTO analysisRevenueDTO = analysisService.calculateRevenueByMonths(year);
        List<Integer> listYear = analysisService.getYearRange();
        analysisRevenueDTO.setListYear(listYear);
        return ResponseEntity.ok(analysisRevenueDTO);
    }

    @GetMapping("/orders")
    @ResponseBody
    public ResponseEntity<AnalysisRevenueDTO> orders(@RequestParam(required = false) Integer year, Model model) {

        AnalysisRevenueDTO analysisOrderDTO = analysisService.countOrdersByMonths(year);
        List<Integer> listYear = analysisService.getYearRange();
        analysisOrderDTO.setListYear(listYear);
        return ResponseEntity.ok(analysisOrderDTO);
    }

    @GetMapping("/sellProducts")
    @ResponseBody
    public ResponseEntity<AnalysisProductDTO> sellProducts(@RequestParam(required = false) Integer year, Model model) {
        List<DataSellProductDTO> listDataSellProductDTOs = analysisService.findTop10BestSellingProducts(year);
        AnalysisProductDTO analysisProductDTO = new AnalysisProductDTO();
        List<Integer> listYear = analysisService.getYearRange();
        analysisProductDTO.setListYear(listYear);
        analysisProductDTO.setListSellProduct(listDataSellProductDTOs);
        return ResponseEntity.ok(analysisProductDTO);
    }
}
