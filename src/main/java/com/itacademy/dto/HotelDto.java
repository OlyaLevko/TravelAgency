package com.itacademy.dto;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.service.CountryService;
import com.itacademy.service.HotelService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
@Component
public class HotelDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank(message = "country must not be blank")
    String country;

    @Min(value = 1,message = "value must be in range from 1 to 5")
    @Max(value = 5,message = "value must be in range from 1 to 5")
    private Integer stars;

    private String picture_url;


    private  CountryService countryService;

    private  HotelService hotelService;


    public HotelDto(String name, String country, Integer stars) {
        this.name = name;
        this.country=country;
        this.stars = stars;
    }

    @Autowired
    public HotelDto(CountryService countryService,HotelService hotelService){
        this.hotelService=hotelService;
        this.countryService=countryService;
    }

    public  Hotel convertToHotel(HotelDto dto){
        Hotel hotel=new Hotel();

        hotel.setId(dto.getId());
        hotel.setName(dto.getName());
        hotel.setStars(dto.getStars());
        hotel.setCountry(countryService.getByCountryName(dto.getCountry()));
        hotel.setPicture_url(dto.getPicture_url());

        return hotel;
    }

    public static HotelDto convertToHotelDto(Hotel hotel){
        HotelDto hotelDto=new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setCountry(hotel.getCountry().getName());
        hotelDto.setStars(hotel.getStars());
        hotelDto.setPicture_url(hotel.getPicture_url());
        return hotelDto;
    }
}
