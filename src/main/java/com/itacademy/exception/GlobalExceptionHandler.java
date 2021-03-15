package com.itacademy.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@ControllerAdvice
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

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handle(NoHandlerFoundException e) {
        ModelAndView modelAndView = new ModelAndView("404", HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handle(AccessDeniedException e) {
        ModelAndView modelAndView = new ModelAndView("403", HttpStatus.FORBIDDEN);
        return modelAndView;
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ModelAndView handle(RepositoryException e){
        ModelAndView modelAndView=new ModelAndView("412",HttpStatus.PRECONDITION_FAILED);
        return modelAndView;
    }

}

