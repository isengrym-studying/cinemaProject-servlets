package com.example.cinema.model.service;

import com.example.cinema.model.dao.UserDao;
import com.example.cinema.validator.EmailValidator;
import com.example.cinema.validator.NameValidator;
import com.example.cinema.validator.PasswordValidator;

public class UserService {
    private final UserDao userDao = UserDao.getInstance();
    private static UserService userService;

    public static synchronized UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public boolean authorize(String email, String password) {
        CipherService cipherService = CipherService.getInstance();
        if (userDao.findUserByEmail(email)) {
            byte[] salt = userDao.getSalt(email);
            byte[] cipheredPassword = cipherService.getEncryptedPassword(password, salt);
            return userDao.confirmUserExistence(email, cipheredPassword);
        }
        return false;
    }
    public boolean signUp(String name, String surname, String email, String password, String role) {

        if (EmailValidator.validate(email)
                && PasswordValidator.validate(password)
                && NameValidator.validate(name)
                && NameValidator.validate(surname)
                && !userDao.findUserByEmail(email))
        {
            CipherService cipherService = CipherService.getInstance();
            byte[] salt = cipherService.generateSalt();
            byte[] cipheredPassword = cipherService.getEncryptedPassword(password, salt);
            return userDao.addUser(name, surname, email, cipheredPassword, salt, role);
        }
        else return false;
    }


}
