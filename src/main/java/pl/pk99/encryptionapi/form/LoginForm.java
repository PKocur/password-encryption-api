package pl.pk99.encryptionapi.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.pk99.encryptionapi.validation.ValidationErrorMessages.FIELD_LENGTH_BETWEEN;
import static pl.pk99.encryptionapi.validation.ValidationErrorMessages.FIELD_REQUIRED;

@Getter
@RequiredArgsConstructor
public class LoginForm {
    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 3, max = 50, message = FIELD_LENGTH_BETWEEN)
    private final String username;

    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 3, max = 256, message = FIELD_LENGTH_BETWEEN)
    private final String password;
}
