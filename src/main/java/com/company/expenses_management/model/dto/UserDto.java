package com.company.expenses_management.model.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String email;
    private String phoneNumber;
}
