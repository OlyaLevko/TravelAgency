package com.itacademy.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "countries")
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z ` \\s -]+")
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Hotel> hotels;
}
