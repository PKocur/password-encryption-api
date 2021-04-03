package pl.pk99.encryptionapi.entity.converter.impl;

import org.springframework.stereotype.Component;
import pl.pk99.encryptionapi.component.SimplePasswordEncoder;
import pl.pk99.encryptionapi.entity.User;
import pl.pk99.encryptionapi.entity.converter.RegistrationFormToUserConverter;
import pl.pk99.encryptionapi.form.RegistrationForm;

@Component
public class RegistrationFormToUserConverterImpl implements RegistrationFormToUserConverter {

    public User convert(RegistrationForm registrationForm, SimplePasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(registrationForm.getUsername());
        byte[] salt = passwordEncoder.generateRandomSalt();
        byte[] password = passwordEncoder.encode(registrationForm.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(password);
        return user;
    }
}
