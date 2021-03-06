package com.itacademy.dto;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.model.Room;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class HotelDto {

    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @Size(min=1,max=5)
    private Integer stars;

    public HotelDto(String name, String country, Integer stars) {
        this.name = name;
        this.country = country;
        this.stars = stars;
    }

    public static Hotel convertToHotel(HotelDto dto){
        Hotel hotel=new Hotel();
        Country country=new Country();
        country.setName(dto.country);

        hotel.setName(dto.name);
        hotel.setCountry(country);
        hotel.setStars(dto.stars);

        return hotel;
    }
}
