package com.santander.user.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{
    static final  String message = "Email already registered";
    final  HttpStatus code;

    public UserNotFoundException() {
        super(message);
        this.code = HttpStatus.NOT_FOUND;
    }
}
