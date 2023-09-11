package com.dembasiby.productmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDTO {
    private HttpStatus status;
    private String message;
}
