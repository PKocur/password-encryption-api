package pl.pk99.encryptionapi.service;

public interface SimplePasswordEncoder {
    byte[] encode(String password, byte[] salt);

    byte[] generateRandomSalt();
}
