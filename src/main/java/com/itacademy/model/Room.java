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

    @Transient
    private Set<LocalDate> bookedDates = new HashSet<>();


    public boolean isAvailable(LocalDate fromDate, LocalDate toDate) {
        LocalDate date;
        for( date = fromDate; date.isBefore(toDate.plusDays(1)); date = date.plusDays(1)){
            if(bookedDates.contains(date)){
                return false;
            }
        }
        return true;
    }

    public void bookDates(LocalDate fromDate, LocalDate toDate) {
        for(LocalDate date = fromDate; date.isBefore(toDate.plusDays(1)); date = date.plusDays(1)){
            bookedDates.add(date);
        }
    }

    public void removeDates(LocalDate fromDate, LocalDate toDate) {
        for(LocalDate date = fromDate; date.isBefore(toDate.plusDays(1)); date = date.plusDays(1)){
            bookedDates.remove(date);
        }
    }
}
