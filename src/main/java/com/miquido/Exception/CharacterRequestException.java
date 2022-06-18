package com.miquido.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CharacterRequestException extends RuntimeException {

    private HttpStatus httpStatus;


    public CharacterRequestException(String message) {
        super(message);
    }

    public CharacterRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
