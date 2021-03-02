package com.itacademy.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"lastName", "firstName", "email", "password", "roleName"})
@ToString(of = {"lastName", "firstName", "email", "password", "roleName"})
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Pattern(regexp = "[A-Z][a-z']+(//s? -? [A-Z][a-z']+)?",
            message = "Must start with a capital letter.")
    @NotNull
    @Column(name = "lastname")
    private String lastName;

    @Pattern(regexp = "[A-Z][a-z']+(//s? -? [A-Z][a-z']+)?",
            message = "Must start with a capital letter.")
    @NotNull
    @Column(name = "firstname")
    private String firstName;

    @Email(message = "Must be a real email.")
    @NotNull
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Pattern(regexp = ".{4,}",
            message = "Must contain at least 8 symbols, one capital letter, one digit, one special character.")
    @Column(name = "password", nullable = false)
    private String password;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_name")
    private Role roleName = Role.USER;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
