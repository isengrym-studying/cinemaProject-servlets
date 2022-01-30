package com.example.cinema.model.service;

import com.example.cinema.model.dao.UserDao;
import com.example.cinema.model.entity.User;
import com.example.cinema.validator.EmailValidator;
import com.example.cinema.validator.NameValidator;
import com.example.cinema.validator.PasswordValidator;

import javax.naming.Name;
import javax.validation.constraints.Email;
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
        if (userDao.findUserByEmail(email)) {
            byte[] salt = userDao.getSalt(email);
            byte[] cipheredPassword = cipherService.getEncryptedPassword(password, salt);
            return userDao.confirmUserExistence(email, cipheredPassword);
        }
        return false;
    }


    public boolean signUp(User givenUser) {
        User user = new User(givenUser);

        CipherService cipherService = CipherService.getInstance();
        byte[] salt = cipherService.generateSalt();
        byte[] cipheredPassword = cipherService.getEncryptedPassword(user.getPassword(), salt);
        return userDao.addUser(user.getName(), user.getSurname(), user.getEmail(), cipheredPassword, salt, user.getRole());

    }

    public List getSignUpIssues(User givenUser) {
        User user = new User(givenUser);
        List<String> list = new ArrayList<>();
        if (!NameValidator.validate(user.getName()) || !NameValidator.validate(user.getSurname())) {
            list.add("nameIssue");
        }
        if (!EmailValidator.validate(user.getEmail())) {
            list.add("emailIssue");
        }
        if (!PasswordValidator.validate(user.getPassword())) {
            list.add("passwordIssue");
        }
        if (userDao.findUserByEmail(user.getEmail())) {
            list.add("userExistsIssue");
        }
        return list;
    }


}
