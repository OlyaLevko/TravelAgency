package com.itacademy.exception;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler{

    @ExceptionHandler(NotSuchElementException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @GetMapping
    public ModelAndView resolve(NotSuchElementException e) {
        ModelAndView modelAndView = new ModelAndView("error-page", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView resolve(UnsupportedOperationException e) {
        ModelAndView modelAndView = new ModelAndView("error-page", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(DateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView resolve(DateFormatException e) {
        ModelAndView modelAndView = new ModelAndView("error-page", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

}

