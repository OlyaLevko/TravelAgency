package com.itacademy.model;


import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"id","price"})
@ToString(of = {"id","price"})
@Table(name = "rooms")
@Entity
public class Room {

    @EmbeddedId
    private RoomCompositeId id;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name = "picture_url",length = 2050)
    private String picture_url;

    @Column(name="price")
    @NotNull
    @Range(min=0)
    private Long price; // in cents


    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<Order> orders;

}
