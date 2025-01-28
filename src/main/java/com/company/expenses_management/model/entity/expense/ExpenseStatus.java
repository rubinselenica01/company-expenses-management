package com.company.expenses_management.model.entity.expense;

import com.company.expenses_management.model.entity.user.Role;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ExpenseStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    DECLINED("DECLINED");

    private final String value;

    ExpenseStatus(String value) {
        this.value = value;
    }

    public static ExpenseStatus fromValue(String value) {
        return Arrays.stream(ExpenseStatus.values())
                .filter(es -> es.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Expense Status %s not found!", value
                )));
    }

}
