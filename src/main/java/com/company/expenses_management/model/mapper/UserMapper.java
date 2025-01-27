package com.company.expenses_management.model.mapper;

import com.company.expenses_management.model.dto.UserCreationFormDto;
import com.company.expenses_management.model.dto.UserDto;
import com.company.expenses_management.model.entity.user.Gender;
import com.company.expenses_management.model.entity.user.Role;
import com.company.expenses_management.model.entity.user.User;

import java.time.LocalDate;
import java.time.Period;

public abstract class UserMapper {

    public static UserDto toDto(User user){

        Period exactAge = Period.between(user.getBirthday(), LocalDate.now());
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender().getValue())
                .age(exactAge.getYears())
                .age(1)
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static User toEntity(UserCreationFormDto userCreationFormDto){
        return User.builder()
                .firstName(userCreationFormDto.getFirstName())
                .lastName(userCreationFormDto.getLastName())
                .gender(Gender.fromValue(userCreationFormDto.getGender()))
                .birthday(LocalDate.parse(userCreationFormDto.getBirthday()))
                .email(userCreationFormDto.getEmail())
                .phoneNumber(userCreationFormDto.getPhoneNumber())
                .role(Role.fromValue(userCreationFormDto.getRole()))
                .build();
    }
}
