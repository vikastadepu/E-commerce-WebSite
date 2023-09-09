package dev.vikas.productservice.Exceptions;

import dev.vikas.productservice.dtos.Exceptiondto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<Exceptiondto> handleNotFoundException(NotFoundException notFoundException)
    {

        return new ResponseEntity(new Exceptiondto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
                HttpStatus.NOT_FOUND);
        //System.out.println("not found exception");
    }

}
