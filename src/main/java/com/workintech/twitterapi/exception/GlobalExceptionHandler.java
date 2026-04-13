package com.workintech.twitterapi.exception;

import com.workintech.twitterapi.exception.base.BaseException;
import com.workintech.twitterapi.exception.notfound.NotFoundException;
import com.workintech.twitterapi.exception.unauthorized.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createError("NOT_FOUND", exception.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorized(UnauthorizedException exception) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(createError("UNAUTHORIZED", exception.getMessage()));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBase(BaseException exception) {

        return ResponseEntity
                .badRequest()
                .body(createError("BAD_REQUEST", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(err ->
                        errors.put(err.getField(), err.getDefaultMessage())
                );

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createError("INTERNAL_ERROR", "Beklenmeyen bir hata oluştu!"));
    }

    private Map<String, String> createError(String type, String message) {

        Map<String, String> error = new HashMap<>();
        error.put("error", type);
        error.put("message", message);

        return error;
    }
}
