package com.company.expenses_management.controller;

import com.company.expenses_management.model.dto.LoginRequestDto;
import com.company.expenses_management.model.dto.UserCreationFormDto;
import com.company.expenses_management.model.dto.UserDto;
import com.company.expenses_management.model.entity.user.User;
import com.company.expenses_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.expenses_management.utils.PathConstants.*;

@Tag(name = "User Controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(basePath)
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;


    @PostMapping(createUser)
    @Operation(summary = "Manager access only : can create user employee")
    public ResponseEntity<String> createUser(@RequestBody UserCreationFormDto u){
        boolean isUserCreated = userService.createUser(u);
        if (isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("User ".concat(u.getRole()).concat(" created successfuly!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some credentials must exist in database!");
    }

    @GetMapping(listAllUsers)
    @Operation(summary = "Manager access only : can see all users")
    public ResponseEntity<List<UserDto>> listAllUsers(){
        return ResponseEntity.ok(userService.listAll());
    }

    @PostMapping(userLogin)
    @Operation(summary = "Both roles access : login ")
    public String login(@RequestBody LoginRequestDto u){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPassword()));
        User user = userService.findByEmail(u.getEmail());

        Instant current = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(current)
                .expiresAt(current.plus(30, ChronoUnit.DAYS))
                .subject(String.valueOf(user.getId()))
                .claim("roles", scope)
                .build();

        JwtEncoderParameters encoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(), claimsSet
        );

        return jwtEncoder.encode(encoderParameters).getTokenValue();

    }
}
