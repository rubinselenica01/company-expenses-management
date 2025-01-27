package com.company.expenses_management.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationFormDto {

    private String firstName;
    private String lastName;
    private String gender;
    private String birthday;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
}
