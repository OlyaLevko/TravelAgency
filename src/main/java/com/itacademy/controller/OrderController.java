package com.itacademy.controller;

import com.itacademy.model.*;
import com.itacademy.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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
    @PreAuthorize(value = "hasAuthority('MANAGER') or #user_id == @userServiceImpl.getByEmail(authentication.name).getId()")
    public String getOrderByUser(@PathVariable Long user_id, Model model){
        model.addAttribute("orders", orderService.getByUserId(user_id));
        model.addAttribute("user", userService.getById(user_id));
        model.addAttribute("days", ChronoUnit.DAYS);
        return "user-orders";
    }

    @GetMapping("/all")
    @PreAuthorize(value = "hasAuthority('MANAGER')")
    public String getAll(Model model){
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("days", ChronoUnit.DAYS);
        return "orders-list";
    }

    @GetMapping("/{id}/cancel")
    @PreAuthorize(value = "hasAuthority('MANAGER') or @orderServiceImpl.getById(#id).getUser().getId() == @userServiceImpl.getByEmail(authentication.name).getId()")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancel(id);
        Long userId = orderService.getById(id).getUser().getId();
        return "redirect:/orders/"+ userId + "/read";
    }

    @GetMapping("/{id}/done")
    @PreAuthorize(value = "hasAuthority('MANAGER')")
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

//   ========================================  room/{room_number}/

    @GetMapping("/make/{country_id}/hotels/{hotel_id}/dates/choose")
    public String chooseBookingDateForRoom(@PathVariable("country_id") Long countryId,
                                       @PathVariable("hotel_id") Long hotelId,
//                                       @PathVariable("room_number") Integer room_number,
                                       Model model){
        model.addAttribute("country_id",countryId);
        model.addAttribute("hotel_id",hotelId);
//        model.addAttribute("room_number",room_number);
        return "choose-date";
    }

//    ==========================================

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
        List<Room> rooms=roomService.getAvailableRoomsInHotelById(hotelId,
                LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dMMMyyyy", Locale.ENGLISH)),
                LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dMMMyyyy", Locale.ENGLISH)));

        model.addAttribute("rooms",rooms);
        return "choose-room";
    }




    @GetMapping("/make/{country_id}/hotels/{hotel_id}/room/{room_number}/user")
    public String addUser(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId,
                          @RequestParam("from_date") String fromDate, @RequestParam("to_date") String toDate,
                          @PathVariable Integer room_number, Authentication auth,Model model){
        if(auth.getAuthorities().contains(Role.MANAGER)) {
            model.addAttribute("user", new User());
            model.addAttribute("from_date",fromDate);
            model.addAttribute("to_date",toDate);
            return "create-user-by-manager";
        }
        else{
            return "redirect:/orders/make/{country_id}/hotels/{hotel_id}/room/{room_number}/user/" + userService.getByEmail(auth.getName()).getId()
                    +"?from_date="+fromDate+"&to_date="+toDate;
        }
    }

    @PostMapping("/make/{country_id}/hotels/{hotel_id}/room/{room_number}/user")
    public String addUser(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId,
                          @RequestParam("from_date") String fromDate, @RequestParam("to_date") String toDate,
                          @Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                          @PathVariable Integer room_number, Model model){
        if(bindingResult.hasErrors()){
            return "create-user-by-manager";
        }
        user = userService.createOrGetByEmail(user);
        return "redirect:/orders/make/{country_id}/hotels/{hotel_id}/room/{room_number}/user/"+user.getId() +
                "?from_date="+fromDate+"&to_date="+toDate;
    }

//    @GetMapping("/make/{country_id}/hotels/{hotel_id}/from/{from_date}/to/{to_date}/room/{room_id}/user/{user_id}")
//    public String chooseRoom(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId,
//                             @PathVariable("from_date") String fromDate, @PathVariable("to_date") String toDate,
//                             Model model, @PathVariable("room_id") Integer room, @PathVariable("user_id") Long userId) {
//        model.addAttribute("user", userService.getById(userId));
//        model.addAttribute("country", countryService.getById(countryId));
//        model.addAttribute("hotel", hotelService.getById(hotelId));
//        model.addAttribute("from_date", fromDate);
//        model.addAttribute("to_date", toDate);
//        model.addAttribute("room", roomService.getById(new RoomCompositeId(hotelService.getById(hotelId), room)));
//        model.addAttribute("days",  LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dMMMyyyy", Locale.ENGLISH))
//                .until(  LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dMMMyyyy", Locale.ENGLISH)),ChronoUnit.DAYS));
//        return "confirm-page";
//    }

//    =====================================

    @GetMapping("/make/{country_id}/hotels/{hotel_id}/room/{room_number}/user/{user_id}")
    public String checkIfRoomIsAvailable(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId,
                                         @RequestParam("from_date") String fromDate, @RequestParam("to_date") String toDate,
                                         @PathVariable("room_number") Integer room_number,
                                         @PathVariable("user_id") Long user_id,
                                         Model model){

        Room checkedRoom=roomService.getById(new RoomCompositeId(hotelService.getById(hotelId),room_number));
        LocalDate from=LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dMMMyyyy", Locale.ENGLISH));
        LocalDate to=LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dMMMyyyy", Locale.ENGLISH));

        if(roomService.checkIfRoomIsAvailable(hotelId,room_number, from,to)) {

            model.addAttribute("user", userService.getById(user_id));
            model.addAttribute("country", countryService.getById(countryId));
            model.addAttribute("hotel", hotelService.getById(hotelId));
            model.addAttribute("from_date", fromDate);
            model.addAttribute("to_date", toDate);
            model.addAttribute("room",  checkedRoom);
            model.addAttribute("days",  LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dMMMyyyy", Locale.ENGLISH))
                    .until(  LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dMMMyyyy", Locale.ENGLISH)),ChronoUnit.DAYS));

            return "confirm-page";

        }else {
            List<Room> rooms=roomService.getAvailableRoomsInHotelById(hotelId,from,to);
            List<List<Room>> roomGroups=new ArrayList<>();
            if(rooms!=null&&!rooms.isEmpty()){
                if(rooms.size()<=3){
                    roomGroups.add(rooms);
                }else {
                    int count=0;
                    for ( count=0; count < rooms.size() - 3; count += 3) {
                        roomGroups.add(rooms.subList(count, count + 3));
                    }
                    roomGroups.add(rooms.subList(count,rooms.size()));
                }
            }

            model.addAttribute("hotel",hotelService.getById(hotelId) );
            model.addAttribute("roomGroups", roomGroups);
            model.addAttribute("country_id",countryId);
            model.addAttribute("message", "Sory, but room: "+room_number+" is not available from: "+fromDate+", to: "+toDate);
            return "rooms-list";
//            return "redirect: /room/"+hotelId+"/all";
        }
    }

//    ================================================/

    @GetMapping("/make/{country_id}/hotels/{hotel_id}/room/{room_id}/user/{user_id}/confirm")
    public String ConfirmOrder(@PathVariable("country_id") Long countryId, @PathVariable("hotel_id") Long hotelId,
                               @RequestParam("from_date") String fromDate, @RequestParam("to_date") String toDate,
                               @PathVariable("room_id") Integer room,
                               @PathVariable("user_id") Long userId){
        Room room1 = roomService.getRoom(hotelId, room);
        Order order = new Order();
        order.setRoom(room1);
        order.setUser(userService.getById(userId));
        order.setFromDate(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dMMMyyyy",Locale.ENGLISH)));
        order.setToDate(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dMMMyyyy",Locale.ENGLISH)));
        orderService.create(order);
        return "redirect: /orders/" + userId + "/read";
    }
}
