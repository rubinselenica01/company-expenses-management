package com.company.expenses_management.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@Getter
public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    PNTS("Prefer Not To Say");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender fromValue(String gender) {
        return Arrays.stream(Gender.values())
                .filter(g -> g.value.equalsIgnoreCase(gender))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Gender %s not found!", gender
                )));
    }
}
