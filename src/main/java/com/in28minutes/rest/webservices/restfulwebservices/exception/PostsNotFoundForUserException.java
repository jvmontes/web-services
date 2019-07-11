package com.in28minutes.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class PostsNotFoundForUserException extends RuntimeException {

    public PostsNotFoundForUserException(String message) {
        super(message);
    }
}

