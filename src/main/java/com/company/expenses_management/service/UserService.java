package com.company.expenses_management.service;

import com.company.expenses_management.model.dto.UserCreationFormDto;
import com.company.expenses_management.model.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    boolean createUser(UserCreationFormDto u);
    User findByEmail(String email);
}
