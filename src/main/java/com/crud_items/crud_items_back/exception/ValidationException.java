package com.crud_items.crud_items_back.exception;

import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        super("Validation failed: " + bindingResult.getErrorCount() + " error(s)");
        this.bindingResult = bindingResult;
    }

}
