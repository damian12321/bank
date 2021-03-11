package pl.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    private ResponseEntity<CustomExceptionHandler> handleException(CustomException e) {
        CustomExceptionHandler studentExceptionHandler = new CustomExceptionHandler(HttpStatus.NOT_FOUND.value(), e.getMessage());
        ResponseEntity<CustomExceptionHandler> responseEntity = new ResponseEntity<>(studentExceptionHandler, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler
    private ResponseEntity<CustomExceptionHandler> handleException(Exception e) {
        CustomExceptionHandler studentExceptionHandler = new CustomExceptionHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        ResponseEntity<CustomExceptionHandler> responseEntity = new ResponseEntity<>(studentExceptionHandler, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
