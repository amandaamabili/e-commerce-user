package com.santander.user.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{
    static final  String message = "User not found";
    final  HttpStatus code;

    public UserNotFoundException() {
        super(message);
        this.code = HttpStatus.NOT_FOUND;
    }
}
