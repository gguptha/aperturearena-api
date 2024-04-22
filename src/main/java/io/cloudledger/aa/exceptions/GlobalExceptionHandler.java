//package io.cloudledger.paperflow.exceptions;
//
//import jakarta.validation.ConstraintViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.FieldError;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.Instant;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    private static ErrorResponse mapToErrorResponse(Exception e, String field, String errorMessage, HttpStatus status) {
//        return ErrorResponse.builder(e, status, errorMessage)
//                .property("property", field)
//                .property("code", status.value())
//                .property("timestamp", Instant.now())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ConstraintViolationException.class)
//    public Map<String,ErrorResponse> handleValidationExceptions(
//            ConstraintViolationException ex) {
//
//        Map<String, ErrorResponse> errors = new HashMap<>();
//        ex.getConstraintViolations().forEach((error) -> {
//            String fieldName = error.getPropertyPath().toString();
//            String errorMessage = error.getMessage();
//            errors.put(fieldName, mapToErrorResponse(ex, fieldName,errorMessage,HttpStatus.BAD_REQUEST));
//        });
//        return errors;
//    }
//
//}
