package com.company.expenses_management.service.impl;

import com.company.expenses_management.model.dto.UserCreationFormDto;
import com.company.expenses_management.model.dto.UserDto;
import com.company.expenses_management.model.entity.user.User;
import com.company.expenses_management.model.mapper.UserMapper;
import com.company.expenses_management.repository.UserRepository;
import com.company.expenses_management.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto findById(UUID uuid) {
        return UserMapper.toDto(userRepository.findById(uuid).get());
    }

    @Override
    public boolean createUser(UserCreationFormDto u) {
        String email = u.getEmail().trim();
        String phoneNumber = u.getPhoneNumber().trim();
        if (userRepository.existsByEmail(email)) {
            log.error("Employee exists with this email");
            return false;
        } else if (userRepository.existsByPhoneNumber(phoneNumber)) {
            log.error("Employee exists with this phone number");
            return false;
        }
        User user = UserMapper.toEntity(u);
        user.setPassword(passwordEncoder.encode(u.getPassword()));

        userRepository.save(user);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public List<UserDto> listAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email: " + email
                ));
    }
}
