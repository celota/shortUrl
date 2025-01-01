package com.meli.urlshortener.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UrlShortenerExceptionHandler {
	
	@ExceptionHandler(UrlNotFoundException.class)
	public ResponseEntity<UrlShortenerError> handleUrlNotFound(UrlNotFoundException ex) {
        UrlShortenerError errorResponse = UrlShortenerError.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<UrlShortenerError>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
