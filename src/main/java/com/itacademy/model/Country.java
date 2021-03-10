package com.itacademy.model;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@ToString(of = {"name"})
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

//    @NotBlank
    @URL
    @Column(name="picture_url",length = 2050)
    private String pictureUrl;

    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Hotel> hotels;
}
