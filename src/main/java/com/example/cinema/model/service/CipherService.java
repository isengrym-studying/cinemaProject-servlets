package com.example.cinema.model.service;

import com.example.cinema.model.service.exceptions.ServiceException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class CipherService {
    private static CipherService cipherService;

    public static synchronized CipherService getInstance() {
        if (cipherService == null) {
            cipherService = new CipherService();
        }
        return cipherService;
    }

    public byte[] getEncryptedPassword(String password, byte[] salt) {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;
        int iterations = 20000;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException("Couldn't encrypt password", e);
        }
    }

    public byte[] generateSalt() {
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Couldn't generate salt", e);
        }
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }
}
