package pl.pk99.encryptionapi.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pk99.encryptionapi.validation.FieldMatches;
import pl.pk99.encryptionapi.validation.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
@FieldMatches(message = "Passwords must match", firstField = "password", secondField = "confirmPassword")
public class RegistrationForm {
    @NotBlank(message = ValidationMessages.FIELD_REQUIRED)
    @Size(min = 3, max = 50, message = ValidationMessages.FIELD_LENGTH_BETWEEN)
    private final String username;

    @NotBlank(message = ValidationMessages.FIELD_REQUIRED)
    @Size(min = 3, max = 256, message = ValidationMessages.FIELD_LENGTH_BETWEEN)
    private final String password;

    @NotBlank(message = ValidationMessages.FIELD_REQUIRED)
    @Size(min = 3, max = 256, message = ValidationMessages.FIELD_LENGTH_BETWEEN)
    private final String confirmPassword;
}
