package com.company.expenses_management.service;

import com.company.expenses_management.model.dto.ReportRequestDto;
import com.company.expenses_management.model.dto.ReportResponseDto;

public interface ReportService {

    ReportResponseDto saveReport(ReportRequestDto reportRequestDto);
}
