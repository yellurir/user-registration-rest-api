package com.gamesys.codingtest.userregistrationrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The BadRequestException class will handle 400 Bad Request client error when an invalid input is provided
 *  @author Revanth Yelluri
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }
}
