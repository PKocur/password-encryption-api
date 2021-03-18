package pl.pk99.encryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pk99.encryptionapi.entity.converter.RegistrationFormToUserConverter;
import pl.pk99.encryptionapi.form.RegistrationForm;
import pl.pk99.encryptionapi.repository.UserRepository;
import pl.pk99.encryptionapi.service.RegistrationService;
import pl.pk99.encryptionapi.service.SimplePasswordEncoder;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final SimplePasswordEncoder simplePasswordEncoder;

    public void registerUser(RegistrationForm registrationForm) {
        RegistrationFormToUserConverter converter = new RegistrationFormToUserConverter();
        userRepository.save(converter.convert(registrationForm, simplePasswordEncoder));
    }
}
