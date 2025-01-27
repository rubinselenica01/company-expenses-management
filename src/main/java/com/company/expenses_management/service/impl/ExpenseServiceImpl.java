package com.company.expenses_management.service.impl;

import com.company.expenses_management.model.dto.ExpenseCreationDto;
import com.company.expenses_management.model.dto.ExpenseDto;
import com.company.expenses_management.model.entity.expense.Expense;
import com.company.expenses_management.model.entity.user.User;
import com.company.expenses_management.model.mapper.ExpenseMapper;
import com.company.expenses_management.repository.ExpenseRepository;
import com.company.expenses_management.repository.UserRepository;
import com.company.expenses_management.security.SecurityUtils;
import com.company.expenses_management.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Override
    public boolean createExpenseRequest(ExpenseCreationDto e) {
        if (e.getExpenseDescription().isEmpty() ||
            e.getAmountToRefund().toString().isEmpty()){
            return false;
        }

        User user = userRepository.findById(Objects.requireNonNull(SecurityUtils.getLoggedUserId())).get();
        Expense expenseEntity = ExpenseMapper.toEntity(e);
        expenseEntity.setEmployee(user);
        expenseEntity.setRefunded(false);

        log.info("Persisting into database expense");
        expenseRepository.save(expenseEntity);
        return true;
    }

    @Override
    public ExpenseDto viewExpenseById(UUID uuid) {
        Expense expense = expenseRepository.findById(uuid).get();
        if (Objects.equals(SecurityUtils.getLoggedUserId(), expense.getEmployee().getId())){
            return ExpenseMapper.toDto(expense);
        }
        throw new RuntimeException("Bad request");
    }

    @Override
    public List<ExpenseDto> listAll() {
        log.info("Listing all the expenses");
        List<Expense> expenseEntities = expenseRepository.findAll();
        return expenseEntities.stream()
                .map(ExpenseMapper::toDto)
                .toList();
    }

    @Override
    public List<ExpenseDto> findAllByFirstNameOrLastName(String text) {
        log.debug("Listing all expenses by search text: {}", text);
        List<Expense> expenseEntities = expenseRepository.findExpensesByEmployeeFirstAndLastName(text);
        return expenseEntities.stream()
                .map(ExpenseMapper::toDto)
                .collect(Collectors.toList());
    }
}
