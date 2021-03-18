package pl.pk99.encryptionapi.error.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final String fieldName;

    public ApplicationException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
}
