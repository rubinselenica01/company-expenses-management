package com.company.expenses_management;

import com.company.expenses_management.model.dto.UserCreationFormDto;
import com.company.expenses_management.model.dto.UserDto;
import com.company.expenses_management.model.entity.user.Gender;
import com.company.expenses_management.model.entity.user.Role;
import com.company.expenses_management.model.entity.user.User;
import com.company.expenses_management.repository.UserRepository;
import com.company.expenses_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.beancontext.BeanContext;

@SpringBootApplication
public class ExpensesManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(ExpensesManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserCreationFormDto user = UserCreationFormDto.builder()
				.firstName("Manager")
				.lastName("1")
				.gender("MALE")
				.birthday("2002-05-07")
				.email("manager@gmail.com")
				.password("HELLO1!")
				.phoneNumber("+355672324860")
				.role("MANAGER")
				.build();
		userService.createUser(user);
	}
}
