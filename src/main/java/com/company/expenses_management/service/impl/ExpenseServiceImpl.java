package com.company.expenses_management.service.impl;

import com.company.expenses_management.model.dto.ExpenseCreationDto;
import com.company.expenses_management.model.dto.ExpenseDto;
import com.company.expenses_management.model.entity.expense.Expense;
import com.company.expenses_management.model.entity.expense.ExpenseStatus;
import com.company.expenses_management.model.entity.user.Role;
import com.company.expenses_management.model.entity.user.User;
import com.company.expenses_management.model.mapper.ExpenseMapper;
import com.company.expenses_management.repository.ExpenseRepository;
import com.company.expenses_management.repository.UserRepository;
import com.company.expenses_management.security.SecurityUtils;
import com.company.expenses_management.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        expenseEntity.setCreatedDate(LocalDate.now());

        log.info("Persisting into database expense");
        expenseRepository.save(expenseEntity);
        return true;
    }

    @Override
    public ExpenseDto viewExpenseById(UUID uuid) {
        Expense expense = expenseRepository.findById(uuid).get();
        if (
                SecurityUtils.getLoggedUserRole().getValue().equals("MANAGER")
                        ||
                        SecurityUtils.getLoggedUser().equals(expense.getEmployee().getId())
        ){
            return ExpenseMapper.toDto(expense);
        }

        throw new RuntimeException("Employee can't see other expenses than his");
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
    public List<ExpenseDto> listAllByUserId(UUID uuid) {
        log.info("Listing all the expenses for emplyee {}",uuid);
        User loggedUser = userRepository.findById(Objects.requireNonNull(SecurityUtils.getLoggedUserId())).get();
        if (loggedUser.getId().equals(uuid) || loggedUser.getRole().equals(Role.MANAGER)){
            List<Expense> expenseEntities = expenseRepository.findAllByEmployeeId(uuid);
            return expenseEntities.stream()
                    .map(ExpenseMapper::toDto)
                    .toList();
        }
        throw new RuntimeException("Bad request");
    }

    @Override
    public void  updateApprovalStatus(UUID expenseId,String status) {
        User user = userRepository.findById(Objects.requireNonNull(SecurityUtils.getLoggedUserId())).get();
        Expense expense = expenseRepository.findById(expenseId).get();
        expense.setStatus(ExpenseStatus.fromValue(status));
        expense.setRefundedBy(user);
        expense.setStatusUpdatedDate(LocalDate.now());
        expenseRepository.save(expense);
    }
}
