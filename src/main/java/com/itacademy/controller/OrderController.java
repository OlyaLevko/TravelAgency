package com.itacademy.controller;

import com.itacademy.model.Order;
import com.itacademy.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;

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

    @GetMapping("{user_id}/add")
    public String makeOrder(@PathVariable Long user_id, Model model){
        Order order1 = orderService.getById(35L);
        order1.getRoom().setBookedDates(new HashSet<>( Arrays.asList(LocalDate.of(2021, 04,10))));
        order1.getRoom().bookDates(LocalDate.of(2021, 3, 15), LocalDate.of(2021,3,16));
        model.addAttribute("order", new Order());
        model.addAttribute("user", userService.getById(user_id));
        model.addAttribute("countries", countryService.getAll());
        return "create-order";
    }

    @PostMapping("{user_id}/add")
    public String chooseCountry(@ModelAttribute("order") Order order, Model model, @PathVariable String user_id){
        model.addAttribute("hotels", hotelService.getByCountry(order.getRoom().getId().getHotel().getCountry().getId()));
        return "create-order";
    }
}
