package com.company.expenses_management.repository;

import com.company.expenses_management.model.entity.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    @Query("SELECT e from Expense e " +
            "LEFT JOIN User u ON e.employee.id = u.id " +
            "WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%',:text,'%')) " +
            "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%',:text,'%')) ")
    List<Expense> findExpensesByEmployeeFirstAndLastName(String text);
}
