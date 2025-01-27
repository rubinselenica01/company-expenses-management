package com.company.expenses_management.model.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExpenseCreationDto {

    private UUID employee_id;
    private String expenseDescription;
    private Double amountToRefund;
    private Boolean refunded;
}
