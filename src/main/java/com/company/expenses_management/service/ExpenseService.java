package com.company.expenses_management.service;

import com.company.expenses_management.model.dto.ExpenseCreationDto;
import com.company.expenses_management.model.dto.ExpenseDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    boolean createExpenseRequest(ExpenseCreationDto e);
    ExpenseDto viewExpenseById(UUID uuid);
    List<ExpenseDto> listAll();
    List<ExpenseDto> listAllByUserId(UUID uuid);
    void updateApprovalStatus(UUID expenseId,String status);
}
