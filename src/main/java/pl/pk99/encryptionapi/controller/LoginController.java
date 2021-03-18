package pl.pk99.encryptionapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.pk99.encryptionapi.error.exception.ApplicationException;
import pl.pk99.encryptionapi.form.LoginForm;
import pl.pk99.encryptionapi.service.LoginService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController extends ControllerExceptionHandler {
    
    private final LoginService loginService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void login(@RequestBody @Valid LoginForm loginForm) throws ApplicationException {
        loginService.login(loginForm);
    }
}
