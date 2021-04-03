package pl.pk99.encryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.pk99.encryptionapi.component.SimplePasswordEncoder;
import pl.pk99.encryptionapi.entity.converter.RegistrationFormToUserConverter;
import pl.pk99.encryptionapi.form.RegistrationForm;
import pl.pk99.encryptionapi.repository.UserRepository;
import pl.pk99.encryptionapi.service.RegistrationService;
import pl.pk99.encryptionapi.validation.Validatable;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final SimplePasswordEncoder simplePasswordEncoder;
    private final RegistrationFormToUserConverter converter;
    private final Validatable<RegistrationForm> registrationFormValidator;

    public void registerUser(RegistrationForm registrationForm, BindingResult bindingResult) {
        registrationFormValidator.validateAndThrow(registrationForm, bindingResult);
        userRepository.save(converter.convert(registrationForm, simplePasswordEncoder));
    }
}
