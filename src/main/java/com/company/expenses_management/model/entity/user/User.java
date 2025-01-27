package com.company.expenses_management.model.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static com.company.expenses_management.utils.SQLConstants.*;


@Builder
@Entity
@Table(name = USER_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = USER_ID_PROPERTY)
    private UUID id;

    @Column(name = USER_NAME_PROPERTY)
    private String firstName;

    @Column(name = USER_SURNAME_PROPERTY)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = USER_GENDER_PROPERTY)
    private Gender gender;

    @Column(name = USER_BIRTHDAY_PROPERTY)
    private LocalDate birthday;

    @Column(name = USER_EMAIL_PROPERTY)
    private String email;

    @Column(name = USER_PASSWORD_PROPERTY)
    private String password;

    @Column(name = USER_PHONE_NUMBER_PROPERTY)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = USER_ROLE_PROPERTY)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.role.name()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
