package com.miquido.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CharacterExceptionHandler {

    @ExceptionHandler(value = CharacterRequestException.class)
    public ResponseEntity<Object> handleCharacterException(CharacterRequestException e) {
        CharacterException characterException = new CharacterException(e.getMessage(),
                e.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));

        return new ResponseEntity<>(characterException, e.getHttpStatus());
    }
}
