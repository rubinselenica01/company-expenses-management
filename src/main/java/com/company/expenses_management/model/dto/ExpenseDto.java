package com.company.expenses_management.model.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
public class ExpenseDto {

    private UUID id;
    private String fullNameEmployee;
    private String expenseDescription;
    private Double amountToRefund;
    private Boolean refunded;

}
