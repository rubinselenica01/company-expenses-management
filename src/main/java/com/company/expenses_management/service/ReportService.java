package com.company.expenses_management.service;

import com.company.expenses_management.model.dto.ReportRequestDto;
import com.company.expenses_management.model.dto.ReportResponseDto;

import java.util.List;

public interface ReportService {

    ReportResponseDto saveReport(ReportRequestDto reportRequestDto);
    List<ReportResponseDto> listAll();
}
