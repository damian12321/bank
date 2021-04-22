package pl.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    private ResponseEntity<CustomExceptionHandler> handleException(CustomException e) {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(HttpStatus.NOT_FOUND.value(), e.getMessage());
        ResponseEntity<CustomExceptionHandler> responseEntity = new ResponseEntity<>(customExceptionHandler, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(NoAccessException.class)
    private ResponseEntity<CustomExceptionHandler> handleException(NoAccessException e) {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        ResponseEntity<CustomExceptionHandler> responseEntity = new ResponseEntity<>(customExceptionHandler, HttpStatus.UNAUTHORIZED);
        return responseEntity;
    }


    @ExceptionHandler
    private ResponseEntity<CustomExceptionHandler> handleException(Exception e) {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        ResponseEntity<CustomExceptionHandler> responseEntity = new ResponseEntity<>(customExceptionHandler, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
