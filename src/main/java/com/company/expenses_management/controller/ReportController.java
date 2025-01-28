package com.company.expenses_management.controller;

import com.company.expenses_management.model.dto.ReportRequestDto;
import com.company.expenses_management.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.company.expenses_management.utils.PathConstants.*;

@Tag(name = "Report Controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(basePath + reportPath)
public class ReportController {

    private final ReportService reportService;

    @PostMapping(name = createReport)
    @Operation(summary = "Manager access onyl : can create reports")
    public ResponseEntity<String> createReport(@RequestBody ReportRequestDto reportRequestDto){
        reportService.saveReport(reportRequestDto);
        return ResponseEntity.ok("Done");
    }


}
