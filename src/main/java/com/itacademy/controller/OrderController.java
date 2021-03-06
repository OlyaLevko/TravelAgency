package com.itacademy.controller;

import com.itacademy.model.Country;
import com.itacademy.model.Order;
import com.itacademy.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;

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

    @GetMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable Long id, Model model) {
        orderService.cancel(id);
        Long userId = orderService.getById(id).getUser().getId();
        return "redirect:/orders/"+ userId + "/read";
    }
    @GetMapping("{user_id}/add")
    public String makeOrder(@PathVariable Long user_id, Model model){
        model.addAttribute("order", new Order());
        model.addAttribute("user", userService.getById(user_id));
        model.addAttribute("countries", countryService.getAll());
        return "create-order";
    }

    @PostMapping("{user_id}/add")
    public String chooseCountry(@RequestParam("country") Country country, Model model, @PathVariable String user_id){
        model.addAttribute("hotels", hotelService.getByCountry(country.getId()));
        return "create-order";
    }
}
