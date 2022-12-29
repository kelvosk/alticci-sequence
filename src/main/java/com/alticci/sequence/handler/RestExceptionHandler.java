package com.alticci.sequence.handler;

import com.alticci.sequence.error.IndexException;
import com.alticci.sequence.error.NegativeIndexException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IndexException.class)
    public ResponseEntity<Object> handleIndexException(IndexException indexException, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                Arrays.asList(indexException.getMessage()));
        return handleExceptionInternal(indexException,
                exceptionResponse.toJson(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NegativeIndexException.class)
    public ResponseEntity<Object> negativeIndexException(NegativeIndexException negativeIndexException, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                Arrays.asList(negativeIndexException.getMessage()));
        return handleExceptionInternal(negativeIndexException,
                exceptionResponse.toJson(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }
}
