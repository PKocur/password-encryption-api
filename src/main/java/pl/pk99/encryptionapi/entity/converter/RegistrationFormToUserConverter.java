package pl.pk99.encryptionapi.entity.converter;

import pl.pk99.encryptionapi.entity.User;
import pl.pk99.encryptionapi.form.RegistrationForm;
import pl.pk99.encryptionapi.service.SimplePasswordEncoder;

public class RegistrationFormToUserConverter {
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
