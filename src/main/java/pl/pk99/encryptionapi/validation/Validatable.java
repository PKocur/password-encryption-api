package pl.pk99.encryptionapi.validation;

import org.springframework.validation.BindingResult;
import pl.pk99.encryptionapi.error.exception.ApiValidationException;

public interface Validatable<T> {
    void validate(T object, BindingResult bindingResult);

    default void validateAndThrow(T object, BindingResult bindingResult) {
        validate(object, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ApiValidationException(bindingResult);
        }
    }
}
