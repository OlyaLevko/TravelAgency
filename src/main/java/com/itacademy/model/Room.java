package com.itacademy.model;


import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
//@EqualsAndHashCode(of = {"number", "hotel"})
//@ToString(of = {"number", "hotel"})
@EqualsAndHashCode(of = {"id","price"})
@ToString(of = {"id","price"})
@Table(name = "rooms")
@Entity
public class Room {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;

    @EmbeddedId
    private RoomCompositeId id;

//    @NotNull
//    @Column(name = "number")
//    private Integer number;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name="price")
    @NotNull
    @Range(min=0)
    private Long price; // in cents

//    @ManyToOne
//    @JoinColumn(name = "hotel_id")
//    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<Order> orders;
}
