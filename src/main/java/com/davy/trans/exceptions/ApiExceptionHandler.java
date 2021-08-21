package com.davy.trans.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "com.davy.trans")
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        HttpServletRequest req = ((ServletWebRequest) request).getRequest();

        ApiErrorResponse apiErrorResponse = ApiErrorResponseBuilder.getInstance()
                .withErrorId("trans -->" + LocalDate.now(ZoneOffset.UTC))
                .forPath(req.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(status.value())
                .build();

        return new ResponseEntity<>(apiErrorResponse, headers, status);
    }


    @ExceptionHandler(EtBadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequestException(EtBadRequestException ex, HttpServletRequest request) {


        ApiErrorResponse apiErrorResponse = ApiErrorResponseBuilder.getInstance()
                .forPath(request.getRequestURI())
                .withMessage(ex.getMessage())
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withErrorId("Trans--> " + LocalDate.now(ZoneOffset.UTC))
                .build();

        return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstrainViolationException(ConstraintViolationException ex, HttpServletRequest request) {

        List<String> errors = ex
                .getConstraintViolations()
                .stream()
                .map(e -> e.getMessage())
                .collect(Collectors.toList());


        ApiErrorResponse apiErrorResponse = ApiErrorResponseBuilder.getInstance()
                .forPath(request.getRequestURI())
                .withMessage(ex.getMessage())
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withErrorId("Trans--> " + LocalDateTime.now(ZoneOffset.UTC))
                .withErrors(errors)
                .build();

        return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex, HttpServletRequest request) {

        List<String> errors = List.of(ex.getMessage());

        ApiErrorResponse apiErrorResponse = ApiErrorResponseBuilder.getInstance()
                .forPath(request.getRequestURI())
                .withMessage(ex.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withErrorId("Trans--> " + LocalDateTime.now(ZoneOffset.UTC))
                .withErrors(errors)
                .build();

        return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
