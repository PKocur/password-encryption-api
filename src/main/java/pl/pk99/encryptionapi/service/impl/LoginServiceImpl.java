package pl.pk99.encryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pk99.encryptionapi.entity.User;
import pl.pk99.encryptionapi.error.exception.ApplicationException;
import pl.pk99.encryptionapi.form.LoginForm;
import pl.pk99.encryptionapi.repository.UserRepository;
import pl.pk99.encryptionapi.service.LoginService;
import pl.pk99.encryptionapi.service.SimplePasswordEncoder;
import pl.pk99.encryptionapi.error.ErrorMessages;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final SimplePasswordEncoder simplePasswordEncoder;

    @Override
    public void login(LoginForm loginForm) {
        User user = getUser(loginForm.getUsername());
        checkIfPasswordIsCorrect(loginForm.getPassword(), user);
    }

    private User getUser(String username) {
        return Optional.ofNullable(userRepository.getByUsername(username)).orElseThrow(() -> {
            throw new ApplicationException(ErrorMessages.USER_NOT_EXISTS, "username");
        });
    }

    private void checkIfPasswordIsCorrect(String password, User user) {
        byte[] hashedPassword = simplePasswordEncoder.encode(password, user.getSalt());
        if (!Arrays.equals(hashedPassword, user.getPassword())) {
            throw new ApplicationException(ErrorMessages.PASSWORD_INCORRECT, "password");
        }
    }
}
