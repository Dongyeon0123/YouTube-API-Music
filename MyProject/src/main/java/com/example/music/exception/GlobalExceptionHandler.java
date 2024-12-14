package com.example.music.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(YouTubeApiException.class)
    public ResponseEntity<String> handleYouTubeApiException(YouTubeApiException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("YouTube API Error: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
    
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<String> handleConversionError(HttpMessageConversionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format: " + ex.getMessage());
    }
}
