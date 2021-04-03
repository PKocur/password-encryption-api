package pl.pk99.encryptionapi.form.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.pk99.encryptionapi.form.RegistrationForm;
import pl.pk99.encryptionapi.validation.Validatable;
import pl.pk99.encryptionapi.validation.ValidationErrorMessages;

@Component
@Getter
@RequiredArgsConstructor
public class RegistrationFormValidator implements Validatable<RegistrationForm> {
    private final static String OBJECT_NAME = "registrationForm";

    @Override
    public void validate(RegistrationForm registrationForm, BindingResult bindingResult) {
        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            bindingResult.addError(new ObjectError(OBJECT_NAME, ValidationErrorMessages.PASSWORDS_MUST_MATCH));
        }
    }
}
