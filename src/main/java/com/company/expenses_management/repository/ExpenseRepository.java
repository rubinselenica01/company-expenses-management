package com.company.expenses_management.repository;

import com.company.expenses_management.model.entity.expense.Expense;
import com.company.expenses_management.model.entity.expense.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    @Query("SELECT e from Expense e " +
            "LEFT JOIN User u ON e.employee.id = u.id " +
            "WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%',:text,'%')) " +
            "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%',:text,'%')) ")
    List<Expense> findExpensesByEmployeeFirstAndLastName(String text);

    List<Expense> findAllByEmployeeId(UUID uuid);


    @Query("SELECT e.amountToRefund from Expense e " +
            "WHERE e.createdDate BETWEEN  :startDate AND :endDate " +
            "AND e.status = :status")
    List<Double> findAmountsToRefundByCreatedDateBetweenAndStatus(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("status") ExpenseStatus expenseStatus);

}
