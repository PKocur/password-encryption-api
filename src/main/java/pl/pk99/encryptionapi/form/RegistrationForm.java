package pl.pk99.encryptionapi.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pk99.encryptionapi.validation.FieldMatches;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.pk99.encryptionapi.validation.ValidationMessages.*;

@Getter
@RequiredArgsConstructor
@FieldMatches(message = PASSWORDS_MUST_MATCH, firstField = "password", secondField = "confirmPassword")
public class RegistrationForm {
    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 3, max = 50, message = FIELD_LENGTH_BETWEEN)
    private final String username;

    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 3, max = 256, message = FIELD_LENGTH_BETWEEN)
    private final String password;

    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 3, max = 256, message = FIELD_LENGTH_BETWEEN)
    private final String confirmPassword;
}
