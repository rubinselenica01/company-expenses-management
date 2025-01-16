package com.company.expenses_management.model.user;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {

    MANAGER("MANAGER"),
    EMPLOYEE("EMPLOYEE");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public static Role fromValue(String role) {
        return Arrays.stream(Role.values())
                .filter(r -> r.value.equals(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Role %s not found!", role
                )));
    }
}
