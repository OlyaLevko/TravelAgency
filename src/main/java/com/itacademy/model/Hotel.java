package com.itacademy.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @OneToMany(mappedBy = "id.hotel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Room> rooms;
}
