package com.company.expenses_management.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReportRequestDto {

    private String startDate;
    private String endDate;
}
