package com.alaaturki.novadesk.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(
            RuntimeException ex
    ){


        ErrorResponse response =
                new ErrorResponse(

                        LocalDateTime.now(),

                        HttpStatus.BAD_REQUEST.value(),

                        ex.getMessage()

                );


        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex
    ){


        String message =
                ex.getBindingResult()
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage();



        ErrorResponse response =
                new ErrorResponse(

                        LocalDateTime.now(),

                        HttpStatus.BAD_REQUEST.value(),

                        message

                );



        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }





    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex
    ){


        ErrorResponse response =
                new ErrorResponse(

                        LocalDateTime.now(),

                        HttpStatus.INTERNAL_SERVER_ERROR.value(),

                        ex.getMessage()

                );



        return new ResponseEntity<>(

                response,

                HttpStatus.INTERNAL_SERVER_ERROR

        );

    }



}