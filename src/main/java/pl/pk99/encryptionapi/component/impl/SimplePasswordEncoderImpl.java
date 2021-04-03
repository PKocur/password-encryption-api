package pl.pk99.encryptionapi.component.impl;

import org.springframework.stereotype.Component;
import pl.pk99.encryptionapi.component.SimplePasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Component
public class SimplePasswordEncoderImpl implements SimplePasswordEncoder {

    private static final String HASHING_ALGORITHM = "PBKDF2WithHmacSHA1";

    @Override
    public byte[] encode(String password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(HASHING_ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Algorithm not available");
        }
    }

    @Override
    public byte[] generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
