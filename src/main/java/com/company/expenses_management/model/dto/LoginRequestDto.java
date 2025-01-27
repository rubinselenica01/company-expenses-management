package com.company.expenses_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
public class LoginRequestDto {

    private final String email;
    private final String password;
}
