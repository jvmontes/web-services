package com.in28minutes.rest.webservices.restfulwebservices.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse  {

    private Date timestamp;
    private String message;
    private String detail;

    ExceptionResponse(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }

}
