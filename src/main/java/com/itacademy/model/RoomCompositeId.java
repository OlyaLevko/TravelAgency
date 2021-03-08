package com.itacademy.model;

import com.itacademy.service.HotelService;
import lombok.*;
import org.hibernate.annotations.OnDelete;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Embeddable

@Setter @Getter
@EqualsAndHashCode(of={"hotel", "number"})
@ToString(of={"hotel", "number"})
@NoArgsConstructor

public class RoomCompositeId implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @NotNull
    @Column(name="number")
    private Integer number;



    public RoomCompositeId (Hotel hotel,Integer number){
        this.hotel=hotel;
        this.number=number;
    }

}
