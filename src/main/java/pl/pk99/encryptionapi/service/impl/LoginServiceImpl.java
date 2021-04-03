package pl.pk99.encryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.pk99.encryptionapi.component.SimplePasswordEncoder;
import pl.pk99.encryptionapi.entity.User;
import pl.pk99.encryptionapi.error.ErrorMessages;
import pl.pk99.encryptionapi.error.exception.ApiException;
import pl.pk99.encryptionapi.form.LoginForm;
import pl.pk99.encryptionapi.repository.UserRepository;
import pl.pk99.encryptionapi.service.LoginService;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final SimplePasswordEncoder passwordEncoder;

    @Override
    public void login(LoginForm loginForm) {
        User user = getUser(loginForm.getUsername());
        checkIfPasswordIsCorrect(loginForm.getPassword(), user);
    }

    private User getUser(String username) {
        return Optional.ofNullable(userRepository.getByUsername(username)).orElseThrow(() -> {
            throw new ApiException(HttpStatus.BAD_REQUEST, "loginForm", ErrorMessages.INVALID_LOGIN_CREDENTIALS);
        });
    }

    private void checkIfPasswordIsCorrect(String password, User user) {
        byte[] hashedPassword = passwordEncoder.encode(password, user.getSalt());
        if (!Arrays.equals(hashedPassword, user.getPassword())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "loginForm", ErrorMessages.INVALID_LOGIN_CREDENTIALS);
        }
    }
}
