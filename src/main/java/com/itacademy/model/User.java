package com.itacademy.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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
    private String firstname;

    @Email(message = "Must be a real email.")
    @NotNull
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Must contain at least 8 symbols, one capital letter, one digit, one special character.")
    private String password;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
