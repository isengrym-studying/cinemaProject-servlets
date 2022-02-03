package com.example.cinema.model.service;

import com.example.cinema.controller.comand.ChangeLanguageCommand;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * The class contains all the logic of ciphering password.
 * It handles data, that is given from DAO, and then sends the result to certain command or another service.
 *
 */
public class CipherService {
    private static CipherService cipherService;
    private static Logger log = Logger.getLogger(CipherService.class);

    public static synchronized CipherService getInstance() {
        if (cipherService == null) {
            cipherService = new CipherService();
        }
        return cipherService;
    }

    /**
     * Method is being used for encrypting given password with encrypting-algorithm and generated salt
     * @return Returns encrypted password as byte[].
     * @exception ServiceException - catches SQLException and throws custom Service-layer exception.
     */
    public byte[] getEncryptedPassword(String password, byte[] salt)
    {
        if (password == null || salt == null) {
            log.error("Null parameters were given to getEncryptedPassword method");
            throw new ServiceException("Null parameters were given");
        }
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

    /**
     * Method is being used for generating salt (Which is then used to encrypt password)
     * @return Returns salt as byte[].
     * @exception ServiceException - catches SQLException and throws custom Service-layer exception.
     */
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
