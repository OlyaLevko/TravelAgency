package com.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView resolve(NotSuchElementException e){
        ModelAndView modelAndView = new ModelAndView("400", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("status",modelAndView.getStatus());
        modelAndView.addObject("exception",e.getClass().toString());
        return modelAndView;
    }


    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView resolve(UnsupportedOperationException e) {
        ModelAndView modelAndView = new ModelAndView("400", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("status", modelAndView.getStatus());
        modelAndView.addObject("exception", e.getClass().toString());
        return modelAndView;
    }
    // інші методи додаватимуться при винекненні необхідності
}

