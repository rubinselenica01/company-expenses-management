package com.company.expenses_management.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ReportResponseDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalAmount;
    private String status;
}
