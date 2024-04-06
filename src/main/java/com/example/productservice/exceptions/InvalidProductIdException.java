package com.example.productservice.exceptions;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidProductIdException extends Exception{
    private Long id;
    public InvalidProductIdException(Long productId ,String message){
        super(message);
        this.id = productId;
    }
}
