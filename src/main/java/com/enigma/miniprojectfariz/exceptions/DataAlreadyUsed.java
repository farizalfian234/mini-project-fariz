package com.enigma.miniprojectfariz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DataAlreadyUsed extends RuntimeException{
    public DataAlreadyUsed(String message) {
        super(message);
    }
}