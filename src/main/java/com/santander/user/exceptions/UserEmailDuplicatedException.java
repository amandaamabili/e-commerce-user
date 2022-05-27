package com.santander.user.exceptions;

import org.springframework.http.HttpStatus;

public class UserEmailDuplicatedException extends RuntimeException {
    static final String message= "Email's user already registered";
    final HttpStatus code;

    public UserEmailDuplicatedException() {
        super(message);
        this.code = HttpStatus.BAD_REQUEST;
    }
}
