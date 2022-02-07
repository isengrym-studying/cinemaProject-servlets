package com.example.cinema.model.service;

import com.example.cinema.model.dao.UserDao;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.validator.EmailValidator;
import com.example.cinema.model.service.validator.NameValidator;
import com.example.cinema.model.service.validator.PasswordValidator;


/**
 * The class contains all the logic for working with users.
 * It handles data, that is given from DAO, and then sends the result to certain command or another service.
 *
 */
public class UserService {
    private final UserDao userDao = UserDao.getInstance();
    private static UserService userService;

    public static synchronized UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public boolean checkUserExistence(String email) {
        UserDao userDao = UserDao.getInstance();
        return userDao.checkUserExistence(email);
    }

    /**
     * Method is the main part of authorization. The given password is being encrypted and then compared with one in DB.
     * @param email email of potential User. (DB TABLE `users` COLUMN `email`)
     * @param password password of potential User. (DB TABLE `users` COLUMN `password`)
     * @return Returns true (If the data has been verified and the user can be authorized).
     * Returns false (If user cannot be authorized).
     * @exception DaoException catches SQLException and throws custom DAO-layer exception.
     */
    public boolean authorize(String email, String password) {
        CipherService cipherService = CipherService.getInstance();
        if (userDao.checkUserExistence(email)) {
            byte[] salt = userDao.getSalt(email);
            byte[] cipheredPassword = cipherService.getEncryptedPassword(password, salt);
            return userDao.confirmUserExistence(email, cipheredPassword);
        }
        return false;
    }


    /**
     * Method is being used for getting User object by email
     * @param email email of potential User. (DB TABLE `users` COLUMN `email`)
     * @return Returns User-object filled with DB data of field, where `email` column equals to given email.
     * Returns empty User-object (If there were no fields with given email)
     */
    public User getUserInstance(String email) {
        return userDao.getUserByEmail(email);
    }


    /**
     * Method is registration itself. The given password is being encrypted, the salt is being created,
     * after what the user is being added to DB. Returns the boolean result of adding user to DB
     * @param name name of potential User. (DB TABLE `users` COLUMN `name`)
     * @param surname surname of potential User. (DB TABLE `users` COLUMN `surname`)
     * @param email email of potential User. (DB TABLE `users` COLUMN `email`)
     * @param password password of potential User. (DB TABLE `users` COLUMN `password`)
     * @param role role of potential User. (DB TABLE `users` COLUMN `role`)
     * @return Returns true (If user was successfully added to DB).
     * Returns false (If user wasn't added to DB).
     *
     */
    public boolean signUp(String name, String surname, String email, String password, String role) {

        CipherService cipherService = CipherService.getInstance();
        byte[] salt = cipherService.generateSalt();
        byte[] cipheredPassword = cipherService.getEncryptedPassword(password, salt);
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(cipheredPassword);
        user.setSalt(salt);
        user.setRole(role);
        return userDao.addUser(user);

    }

    /**
     * The method is being used for validating information given by user of application.
     * Validators are used to find issues. Final result is boolean value
     * @param name name of potential User. (DB TABLE `users` COLUMN `name`)
     * @param surname surname of potential User. (DB TABLE `users` COLUMN `surname`)
     * @param email email of potential User. (DB TABLE `users` COLUMN `email`)
     * @param password password of potential User. (DB TABLE `users` COLUMN `password`)
     * @return Returns true (If there were no issues).
     * Returns false (If there are issues).
     *
     */
    public boolean signUpDataValid(String name, String surname, String email, String password) {

        if (!NameValidator.validate(name) || !NameValidator.validate(surname)) {
            return false;
        }
        if (!EmailValidator.validate(email)) {
            return false;
        }
        if (!PasswordValidator.validate(password)) {
            return false;
        }
        if (userDao.checkUserExistence(email)) {
            return false;
        }
        return true;
    }

    public boolean updateUser(User user) {
        UserDao userDao = UserDao.getInstance();
        return userDao.updateUser(user);
    }

    public boolean deleteUser(User user) {
        UserDao userDao = UserDao.getInstance();
        return userDao.deleteUser(user);
    }


}
