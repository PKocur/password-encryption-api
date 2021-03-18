package pl.pk99.encryptionapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.pk99.encryptionapi.form.RegistrationForm;
import pl.pk99.encryptionapi.service.RegistrationService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegistrationController extends ControllerExceptionHandler {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegistrationForm registrationForm) {
        registrationService.registerUser(registrationForm);
    }
}
