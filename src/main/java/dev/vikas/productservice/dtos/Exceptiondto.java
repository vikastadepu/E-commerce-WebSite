package dev.vikas.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class Exceptiondto {
    private HttpStatus errorCode;
    private String message;

    public Exceptiondto(HttpStatus status, String message)
    {
        this.errorCode=status;
        this.message=message;
    }
}
