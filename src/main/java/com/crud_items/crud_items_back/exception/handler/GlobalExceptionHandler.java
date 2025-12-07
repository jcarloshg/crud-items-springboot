package com.crud_items.crud_items_back.exception.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud_items.crud_items_back.exception.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {
        model.addAttribute("message", "There are validation errors");
        model.addAttribute("errors", ex.getBindingResult().getAllErrors());
        return "error/validation";
    }

}
