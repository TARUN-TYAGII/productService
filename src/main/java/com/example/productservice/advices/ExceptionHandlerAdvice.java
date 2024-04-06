package com.example.productservice.advices;


import com.example.productservice.dtos.ArithmeticExceptionDto;
import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException(){
        ArithmeticExceptionDto dto = new ArithmeticExceptionDto();
        dto.setMessage("Dividing by zero");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> handleInvalidProductIdException(InvalidProductIdException invalidProductIdException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Invalid product Id");
        exceptionDto.setProductId(invalidProductIdException.getId());
        return new ResponseEntity<>(exceptionDto , HttpStatus.BAD_REQUEST);
    }
}
