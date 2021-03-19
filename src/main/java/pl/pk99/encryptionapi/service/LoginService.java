package pl.pk99.encryptionapi.service;

import pl.pk99.encryptionapi.form.LoginForm;

public interface LoginService {
    /**
     * Performs login operation - checks the correctness of the provided data by searching for the user in the database.
     *
     * @param loginForm user form used for login
     */
    void login(LoginForm loginForm);
}
