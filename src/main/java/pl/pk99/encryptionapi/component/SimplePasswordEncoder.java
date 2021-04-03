package pl.pk99.encryptionapi.component;

public interface SimplePasswordEncoder {
    byte[] encode(String password, byte[] salt);

    byte[] generateRandomSalt();
}
