package com.eobrazovanje.eobrazovanje_api.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.eobrazovanje.eobrazovanje_api.exceptions.dtos.ApiError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage(), List.of());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s",
                ex.getValue(), ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");

        return buildError(HttpStatus.BAD_REQUEST, "Bad Request", msg, List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + " " + err.getDefaultMessage())
                .collect(Collectors.toList());

        return buildError(HttpStatus.BAD_REQUEST, "Validation Failed", "One or more fields are invalid", details);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleInvalidFormat(HttpMessageNotReadableException ex) {
        String message = "Invalid request payload";

        // If cause is InvalidFormatException, give a better message
        if (ex.getCause() instanceof InvalidFormatException ife) {
            String fieldName = ife.getPath().stream()
                    .map(ref -> ref.getFieldName())
                    .findFirst()
                    .orElse("unknown field");

            message = String.format(
                "Invalid value '%s' for field '%s'. Expected type: %s",
                ife.getValue(),
                fieldName,
                ife.getTargetType().getSimpleName()
            );
        }

        return buildError(HttpStatus.BAD_REQUEST, "Bad Request", message, List.of());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception ex) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                "An unexpected error occurred", List.of());
    }

    private ResponseEntity<ApiError> buildError(HttpStatus status, String error, String message, List<String> details) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                status.value(),
                error,
                message,
                details
        );

        return ResponseEntity.status(status).body(apiError);
    }
}
