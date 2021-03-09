package com.itacademy.dto;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class HotelDto {

    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @Min(value = 1)
    @Max(value = 5)
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
