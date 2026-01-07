package com.desafio.itau.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ProblemDetail ReportException(MethodArgumentNotValidException e, HttpServletRequest request) {


        var errors = e.getBindingResult().getAllErrors()
                .stream()
                .map(GeneralExceptionHandler::apply);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Validation errors");
        problemDetail.setDetail("Invalid request parameters.");
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("errors", errors);
        problemDetail.setType(URI.create("https://www.itau.com.br/atendimento-itau/para-voce"));

        return problemDetail;
    }

    private static Map.Entry<String, String> apply(ObjectError error) {
        return Map.entry(((FieldError) error).getField(), Objects.requireNonNull(error.getDefaultMessage()));
    }
}
