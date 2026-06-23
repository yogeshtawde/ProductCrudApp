package in.belenits.controller;

import in.belenits.exception.ErrorDetails;
import in.belenits.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDetails>
    handleProductNotFoundException(ProductNotFoundException ex)
    {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex)
    {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}