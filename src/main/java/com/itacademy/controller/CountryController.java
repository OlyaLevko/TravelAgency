package com.itacademy.controller;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;


    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{id}/hotels")
    public String countryAllHotelsList(@PathVariable Long id, Model model){
        Country country=countryService.getById(id);
        log.info("============ "+country.toString());

        List<Hotel> hotels=countryService.getAllHotelsInCountry(country.getName());
        List<List<Hotel>> hotelGroups=new ArrayList<>();
        if(hotels!=null&&!hotels.isEmpty()){
            if(hotels.size()<=3){
                hotelGroups.add(hotels);
            }else {
                int count=0;
                for ( count=0; count < hotels.size() - 3; count += 3) {
                    hotelGroups.add(hotels.subList(count, count + 3));
                }
                hotelGroups.add(hotels.subList(count,hotels.size()));
            }
        }

        model.addAttribute("country", country);
        model.addAttribute("hotelGroups", hotelGroups);
        return "country-hotels-list";
    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/hotels/manager")
    public String countryAllHotelsListForManager(@PathVariable Long id, Model model){
        Country country=countryService.getById(id);
        log.info("============ "+country.toString()+"from Manager page");

        List<Hotel> hotels=countryService.getAllHotelsInCountry(country.getName());

        model.addAttribute("country", country);
        model.addAttribute("hotels", hotels);
        return "country-hotels-manager";
    }
    @GetMapping("/all")
    public String getCountryList(Model model){
        List<Country> countries=countryService.getAll();
        List<List<Country>> countriesGroups=new ArrayList<>();
        if(countries!=null&&!countries.isEmpty()){
            if(countries.size()<=3){
                countriesGroups.add(countries);
            }else {
                int count=0;
                for ( count=0; count < countries.size() - 3; count += 3) {
                    countriesGroups.add(countries.subList(count, count + 3));
                }
                countriesGroups.add(countries.subList(count,countries.size()));
            }
        }
        model.addAttribute("countriesGroups",countriesGroups);
        return "country-list";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/{country_id}/delete")
    public String deleteCountry(@PathVariable Long country_id){
        countryService.delete(country_id);
        return "redirect:/country/all";
    }

}
