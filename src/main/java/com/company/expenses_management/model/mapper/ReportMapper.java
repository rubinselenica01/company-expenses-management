package com.company.expenses_management.model.mapper;

import com.company.expenses_management.model.dto.ReportRequestDto;
import com.company.expenses_management.model.dto.ReportResponseDto;
import com.company.expenses_management.model.entity.Report;
import com.company.expenses_management.model.entity.expense.ExpenseStatus;

import java.time.LocalDate;

public abstract class ReportMapper {

    public static Report toEntity(ReportRequestDto rDto){
            return Report.builder()
                    .startDate(LocalDate.parse(rDto.getStartDate()))
                    .endDate(LocalDate.parse(rDto.getEndDate()))
                    .build();
    }

    public static ReportResponseDto toDto(Report report){
        return ReportResponseDto.builder()
                .startDate(report.getStartDate())
                .endDate(report.getEndDate())
                .totalAmount(report.getTotalAmountRefunded())
                .status(ExpenseStatus.APPROVED.getValue())
                .build();
    }
}
