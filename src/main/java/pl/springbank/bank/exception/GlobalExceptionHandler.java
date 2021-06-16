package pl.springbank.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LockedException.class)
    private ResponseEntity<CustomExceptionHandler> handleException(LockedException e) {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(new Date(),HttpStatus.LOCKED.value(), e.getMessage());
        return new ResponseEntity<>(customExceptionHandler, HttpStatus.LOCKED);
    }

    @ExceptionHandler(NoAccessException.class)
    private ResponseEntity<CustomExceptionHandler> handleException(NoAccessException e) {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(new Date(),HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(customExceptionHandler, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(NoResourcesException.class)
    private ResponseEntity<CustomExceptionHandler> handleException(NoResourcesException e) {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(new Date(),HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(customExceptionHandler, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    private ResponseEntity<CustomExceptionHandler> handleException(Exception e) {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(new Date(),HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(customExceptionHandler, HttpStatus.BAD_REQUEST);
    }
}
