package com.company.expenses_management.service;

import com.company.expenses_management.model.dto.UserCreationFormDto;
import com.company.expenses_management.model.dto.UserDto;
import com.company.expenses_management.model.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    UserDto findById(UUID uuid);
    boolean createUser(UserCreationFormDto u);
    User findByEmail(String email);
    List<UserDto> listAll();
}
