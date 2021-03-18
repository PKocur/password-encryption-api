package pl.pk99.encryptionapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pk99.encryptionapi.error.exception.ApplicationException;

import java.util.HashMap;
import java.util.Map;

public abstract class ControllerExceptionHandler {

    private static final String DEFAULT_ERROR_NAME = "error";
    private static final String DEFAULT_ERROR_MESSAGE = "Other error occurred";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Map<String, String>> handleValidationErrors(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            String fieldName = error.getObjectName();
            if(error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            }
            String message = error.getDefaultMessage();
            validationErrors.put(fieldName, message);
        }
        return wrapIntoErrors(validationErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicationException.class)
    public Map<String, Map<String, String>> handleApplicationError(ApplicationException exception) {
        Map<String, String> applicationError = new HashMap<>();
        applicationError.put(exception.getFieldName(), exception.getMessage());
        return wrapIntoErrors(applicationError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Map<String, Map<String, String>> handleOtherError(Exception exception) {
        Map<String, String> otherError = new HashMap<>();
        otherError.put(DEFAULT_ERROR_NAME, DEFAULT_ERROR_MESSAGE);
        return wrapIntoErrors(otherError);
    }

    private Map<String, Map<String, String>> wrapIntoErrors(Map<String, String> errorMap) {
        Map<String, Map<String, String>> errors = new HashMap<>();
        errors.put("errors", errorMap);
        return errors;
    }
}
