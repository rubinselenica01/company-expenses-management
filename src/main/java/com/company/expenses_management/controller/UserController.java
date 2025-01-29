package com.company.expenses_management.controller;

import com.company.expenses_management.model.dto.LoginRequestDto;
import com.company.expenses_management.model.dto.UserCreationFormDto;
import com.company.expenses_management.model.dto.UserDto;
import com.company.expenses_management.model.entity.user.Role;
import com.company.expenses_management.model.entity.user.User;
import com.company.expenses_management.security.SecurityUtils;
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
import java.util.UUID;
import java.util.stream.Collectors;

import static com.company.expenses_management.utils.PathConstants.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(basePath)
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;


    @PostMapping(createUser)
    public ResponseEntity<String> createUser(@RequestBody UserCreationFormDto u){
        boolean isUserCreated = userService.createUser(u);
        if (isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("User ".concat(u.getRole()).concat(" created successfuly!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some credentials must exist in database!");
    }

    @GetMapping(getUserProfile)
    public ResponseEntity<UserDto> getProfile(@PathVariable("id") UUID id){
        if (
                SecurityUtils.getLoggedUserRole().getValue().equals("MANAGER")
                ||
                SecurityUtils.getLoggedUser().equals(id)
        ){
            return ResponseEntity.ok(userService.findById(id));
        }
        throw new RuntimeException("Can't load profile!");
    }

    @GetMapping(listAllUsers)
    public ResponseEntity<List<UserDto>> listAllUsers(){
        return ResponseEntity.ok(userService.listAll());
    }

    @PostMapping(userLogin)
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
