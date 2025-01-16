package com.company.expenses_management.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.company.expenses_management.utils.Constants.*;


@Entity
@Table(name = USER_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

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
    private LocalDateTime birthday;

    @Column(name = USER_EMAIL_PROPERTY)
    private String email;

    @Column(name = USER_PASSWORD_PROPERTY)
    private String password;

    @Column(name = USER_PHONE_NUMBER_PROPERTY)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = USER_ROLE_PROPERTY)
    private Role role;

}
