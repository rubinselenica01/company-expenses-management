package com.company.expenses_management.service.impl;

import com.company.expenses_management.model.dto.ReportRequestDto;
import com.company.expenses_management.model.dto.ReportResponseDto;
import com.company.expenses_management.model.entity.Report;
import com.company.expenses_management.model.entity.expense.Expense;
import com.company.expenses_management.model.entity.expense.ExpenseStatus;
import com.company.expenses_management.model.mapper.ReportMapper;
import com.company.expenses_management.repository.ExpenseRepository;
import com.company.expenses_management.repository.ReportRepository;
import com.company.expenses_management.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public ReportResponseDto saveReport(ReportRequestDto reportRequestDto) {
        Report report = ReportMapper.toEntity(reportRequestDto);
        List<Double> expenses = expenseRepository.findAmountsToRefundByCreatedDateBetweenAndStatus(
                report.getStartDate(),
                report.getEndDate(),
                ExpenseStatus.APPROVED);

        Double totalAmount = expenses.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        report.setTotalAmountRefunded(totalAmount);

        return ReportMapper.toDto(reportRepository.save(report));
    }

}
