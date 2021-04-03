package pl.pk99.encryptionapi.entity.converter;

import org.springframework.stereotype.Component;
import pl.pk99.encryptionapi.component.SimplePasswordEncoder;
import pl.pk99.encryptionapi.entity.User;
import pl.pk99.encryptionapi.form.RegistrationForm;

@Component
public interface RegistrationFormToUserConverter {
    User convert(RegistrationForm registrationForm, SimplePasswordEncoder passwordEncoder);
}
