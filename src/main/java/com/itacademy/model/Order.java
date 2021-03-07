package com.itacademy.model;


import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "orders")
@Entity
public class Order {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "hotel_id"),
            @JoinColumn(name="number")
    })
    private Room room;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column (name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

}
