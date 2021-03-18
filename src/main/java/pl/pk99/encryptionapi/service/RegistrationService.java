package pl.pk99.encryptionapi.service;

import pl.pk99.encryptionapi.form.RegistrationForm;

public interface RegistrationService {
    /**
     * Performs register operation - checks the correctness of the provided data and saves the new user into the database.
     *
     * @param registrationForm user form used for registration
     */
    void registerUser(RegistrationForm registrationForm);
}
