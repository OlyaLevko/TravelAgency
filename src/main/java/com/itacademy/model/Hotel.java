package com.itacademy.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"name", "country"})
@ToString(of = {"name", "country"})
@Table(name = "hotels")
@Entity
public class Hotel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "stars")
    @Min(value = 1)
    @Max(value = 5)
    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Room> rooms;
}
