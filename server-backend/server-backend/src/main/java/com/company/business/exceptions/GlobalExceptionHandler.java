package com.company.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Global exception handler for the application.
 * Catches exceptions thrown by controllers and formats a proper HTTP response.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    // This method will handle all MethodArgumentNotValidException exceptions,
    // which are thrown when @Valid validation on a controller method argument fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // ex.getBindingResult() gives access to the results of the failed validation
        // getAllErrors() returns a list of all validation errors (could be multiple fields)
        // get(0) picks the first error in that list
        // getDefaultMessage() retrieves the human-readable message defined in the @NotBlank, @Size, etc.
        String errorMessage = ex.getBindingResult()
            .getAllErrors()
            .get(0)
            .getDefaultMessage();

        return ResponseEntity
            .badRequest()
            .body(new ApiErrorResponse(errorMessage));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex) {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ApiErrorResponse(ex.getMessage()));
    }
}
