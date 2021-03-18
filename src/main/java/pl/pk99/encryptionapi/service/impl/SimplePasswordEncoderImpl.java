package pl.pk99.encryptionapi.service.impl;

import org.springframework.stereotype.Service;
import pl.pk99.encryptionapi.service.SimplePasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class SimplePasswordEncoderImpl implements SimplePasswordEncoder {

    private static final String HASHING_ALGORITHM = "SHA-512";

    @Override
    public byte[] encode(String password, byte[] salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(HASHING_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not available");
        }
        md.update(salt);
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public byte[] generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
