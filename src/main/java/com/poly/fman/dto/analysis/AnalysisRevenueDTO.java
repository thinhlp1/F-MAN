package com.poly.fman.dto.analysis;

import java.util.List;

import com.poly.fman.dto.model.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisRevenueDTO implements ResponseDTO{
    List<Object> listdataRevenueByMonths;
     List<Integer> listYear;
}
