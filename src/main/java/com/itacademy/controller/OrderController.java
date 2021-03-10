package com.itacademy.controller;

import com.itacademy.model.Country;
import com.itacademy.model.Order;
import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;
import com.itacademy.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final HotelService hotelService;
    private final CountryService countryService;
    private final RoomService roomService;

    public OrderController(OrderService orderService, UserService userService, HotelService hotelService, CountryService countryService, RoomService roomService) {
        this.orderService = orderService;
        this.userService = userService;
        this.hotelService = hotelService;
        this.countryService = countryService;
        this.roomService = roomService;
    }

    @GetMapping("/{user_id}/read")
    public String getOrderByUser(@PathVariable Long user_id, Model model){
        model.addAttribute("orders", orderService.getByUserId(user_id));
        model.addAttribute("user", userService.getById(user_id));
        model.addAttribute("days", ChronoUnit.DAYS);
        return "user-orders";
    }

    @GetMapping("/all")
    public String getAll(Model model){
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("days", ChronoUnit.DAYS);
        return "orders-list";
    }

    @GetMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancel(id);
        Long userId = orderService.getById(id).getUser().getId();
        return "redirect:/orders/"+ userId + "/read";
    }

    @GetMapping("/{id}/done")
    public String doneOrder(@PathVariable Long id) {
        orderService.done(id);
        Long userId = orderService.getById(id).getUser().getId();
        return "redirect:/orders/"+ userId + "/read";
    }

   @GetMapping("/make")
    public String chooseCountry(Model model){
        model.addAttribute("countries", countryService.getAll());
        return "choose-country";
    }

   @GetMapping("/make/{country_id}/hotels")
   public String chooseHotel(Model model, @PathVariable("country_id") Long countryId){
       model.addAttribute("hotels", hotelService.getByCountry(countryId));
       return "choose-hotel";
   }

   @GetMapping("/make/{country_id}/hotels/{hotel_id}/from")
    public String chooseDate(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId){
        return "choose-date";
   }
    @PostMapping("/make/{country_id}/hotels/{hotel_id}/from")
    public String chooseDate(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId,
                             @RequestParam("from_date") String fromDate, @RequestParam("to_date") String toDate,
                             Model model){
        return "redirect: /orders/make/" + countryId+ "/hotels/" + hotelId + "/from/" + fromDate + "/to/" + toDate;

    }

    @GetMapping("/make/{country_id}/hotels/{hotel_id}/from/{from_date}/to/{to_date}")
    public String chooseRoom(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId,
                             @PathVariable("from_date") String fromDate, @PathVariable("to_date") String toDate,
                             Model model){
        model.addAttribute("rooms",
                roomService.getAvailableRoomsInHotelById(hotelId,
                        LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("ddMMMyyyy")),
                        LocalDate.parse(toDate, DateTimeFormatter.ofPattern("ddMMMyyyy"))));
        return "choose-room";
    }

    @GetMapping("/make/{country_id}/hotels/{hotel_id}/from/{from_date}/to/{to_date}/room/{room_id}")
    public String chooseRoom(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId,
                             @PathVariable("from_date") String fromDate, @PathVariable("to_date") String toDate,
                             Model model, @PathVariable("room_id") Integer room) {
        model.addAttribute("country", countryService.getById(countryId));
        model.addAttribute("hotel", hotelService.getById(hotelId));
        model.addAttribute("from_date", fromDate);
        model.addAttribute("to_date", toDate);
        model.addAttribute("room", roomService.getById(new RoomCompositeId(hotelService.getById(hotelId), room)));
        return "confirm-page";
    }


}
