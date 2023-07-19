package com.poly.fman.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.fman.dto.analysis.DashBoardOverviewDTO;
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

    @GetMapping("/revenue")
    public String revenue(@RequestParam(required = false) Integer year, Model model) {

        List<DataDTO> listDataRevenueByMonths = analysisService.calculateRevenueByMonths(year);
        List<Integer> listYear = analysisService.getYearRange();
        DataDTO dataRevenueByMonths = listDataRevenueByMonths.get(0);
        DataDTO dataRevenueByMonthsStr = listDataRevenueByMonths.get(1);
        List<Object> LdataRevenueByMonths = Arrays.asList(dataRevenueByMonths.getDataMonths());
        List<Object> LdataRevenueByMonthsStr = Arrays.asList(dataRevenueByMonthsStr.getDataMonths());
        model.addAttribute("dataRevenueByMonths", LdataRevenueByMonths);
        model.addAttribute("dataRevenueByMonthsStr", LdataRevenueByMonthsStr);
        model.addAttribute("listYear", listYear);
         model.addAttribute("year", year);
        return "admin/layout/Analysis/analysis_revenue";
    }

    @GetMapping("/orders")
    public String orders(@RequestParam(required = false) Integer year, Model model) {

        List<DataDTO> listDataOrderByMonths = analysisService.countOrdersByMonths(year);
        DataDTO dataOrderByMonths = listDataOrderByMonths.get(0);
        List<Object> LdataOrderByMonths = Arrays.asList(dataOrderByMonths.getDataMonths());
        List<Integer> listYear = analysisService.getYearRange();
        model.addAttribute("dataOrderByMonths", LdataOrderByMonths);
        model.addAttribute("listYear", listYear);
         model.addAttribute("year", year);
        return "admin/layout/Analysis/analysis_orders";
    }

    @GetMapping("/sellProducts")
    public String sellProducts(@RequestParam(required = false) Integer year, Model model) {
        List<DataSellProductDTO> listDataSellProductDTOs = analysisService.findTop10BestSellingProducts(year);
        List<Integer> listYear = analysisService.getYearRange();
        model.addAttribute("dataSellProductDTOs", listDataSellProductDTOs);
        model.addAttribute("listYear", listYear);
         model.addAttribute("year", year);
        return "admin/layout/Analysis/analysis_sell_product";
    }
}
