package dev.ren.productCatalog.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleNotFoundException(ResourceNotFoundException e){
        System.out.println("Invoking the exception handler method for ResourceNotFoundException");
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND,e.getMessage()),HttpStatus.NOT_FOUND);
    }
}
