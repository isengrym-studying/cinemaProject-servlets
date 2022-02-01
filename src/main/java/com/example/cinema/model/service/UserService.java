package com.example.cinema.model.service;

import com.example.cinema.model.dao.UserDao;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.validator.EmailValidator;
import com.example.cinema.model.service.validator.NameValidator;
import com.example.cinema.model.service.validator.PasswordValidator;

import java.util.ArrayList;
import java.util.List;

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
        if (userDao.checkUserExistence(email)) {
            byte[] salt = userDao.getSalt(email);
            byte[] cipheredPassword = cipherService.getEncryptedPassword(password, salt);
            return userDao.confirmUserExistence(email, cipheredPassword);
        }
        return false;
    }

    public User getUserInstance(String email) {
        return userDao.getUserByEmail(email);
    }


    public boolean signUp(String name, String surname, String email, String password, String role) {

        CipherService cipherService = CipherService.getInstance();
        byte[] salt = cipherService.generateSalt();
        byte[] cipheredPassword = cipherService.getEncryptedPassword(password, salt);
        return userDao.addUser(name, surname, email, cipheredPassword, salt, role);

    }

    public List getSignUpIssues(String name, String surname, String email, String password, String role) {

        List<String> list = new ArrayList<>();
        if (!NameValidator.validate(name) || !NameValidator.validate(surname)) {
            list.add("nameIssue");
        }
        if (!EmailValidator.validate(email)) {
            list.add("emailIssue");
        }
        if (!PasswordValidator.validate(password)) {
            list.add("passwordIssue");
        }
        if (userDao.checkUserExistence(email)) {
            list.add("userExistsIssue");
        }
        return list;
    }


}
