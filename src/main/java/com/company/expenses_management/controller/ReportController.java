package com.company.expenses_management.controller;

import com.company.expenses_management.model.dto.ReportRequestDto;
import com.company.expenses_management.model.dto.ReportResponseDto;
import com.company.expenses_management.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.expenses_management.utils.PathConstants.*;

@Tag(name = "Report Controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(basePath + reportPath)
public class ReportController {

    private final ReportService reportService;

    @PostMapping(createReport)
    @Operation(summary = "Manager access only : can create reports")
    public ResponseEntity<ReportResponseDto> createReport(@RequestBody ReportRequestDto reportRequestDto){
        return ResponseEntity.ok(reportService.saveReport(reportRequestDto));
    }

    @GetMapping(listAllReports)
    @Operation(summary = "Manager access only : can list all reports")
    public ResponseEntity<List<ReportResponseDto>> listAllReports(){
        return ResponseEntity.ok(reportService.listAll());
    }


}
