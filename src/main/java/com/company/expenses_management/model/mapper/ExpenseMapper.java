package com.company.expenses_management.model.mapper;

import com.company.expenses_management.model.dto.ExpenseCreationDto;
import com.company.expenses_management.model.dto.ExpenseDto;
import com.company.expenses_management.model.entity.expense.Expense;
import com.company.expenses_management.security.SecurityUtils;

import java.time.LocalDate;

public abstract class ExpenseMapper {

    public static Expense toEntity(ExpenseCreationDto expenseCreationDto){
        return Expense.builder()
                .expenseDescription(expenseCreationDto.getExpenseDescription())
                .amountToRefund(expenseCreationDto.getAmountToRefund())
                .refunded(expenseCreationDto.getRefunded())
                .build();
    }

    public static ExpenseDto toDto(Expense expense) {
        return ExpenseDto.builder()
                .id(expense.getId())
                .fullNameEmployee(expense.getEmployee().getFirstName()
                        .concat(" ")
                        .concat(expense.getEmployee().getLastName()))
                .expenseDescription(expense.getExpenseDescription())
                .amountToRefund(expense.getAmountToRefund())
                .refunded(expense.getRefunded())
                .createdDate(expense.getCreatedDate())
                .statusUpdatedDate(expense.getStatusUpdatedDate())
                .build();

    }
}
