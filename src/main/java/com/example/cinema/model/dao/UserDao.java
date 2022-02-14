package com.example.cinema.model.dao;

import com.example.cinema.model.dao.exceptions.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for User-entity. (Singleton pattern is implemented)
 *
 */
public class UserDao {
    private static Logger log = Logger.getLogger(UserDao.class);
    private static UserDao userDao;

    public static synchronized UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    private UserDao() {}

    /**
     * Method is being used to find if there is user with given email value in DB's `users` table
     * @param email email of potential User. (DB TABLE `users` COLUMN `email`)
     * @return true (if field with given value was found), false (if field with given value wasn't found)
     * @throws DaoException catches SQLException and throws custom DAO-layer exception
     */
    public boolean checkUserExistence(String email) {
        log.info("Checking existence of email in DB ("+ email + ")");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.FIND_USER_BY_EMAIL)) {

            statement.setString(1, email);

            try (ResultSet resSet = statement.executeQuery()) {
                return resSet.next();
            }

        } catch (SQLException e) {
            log.error("SQLException in UserDao.checkUserExistence() " + e.getMessage());
            throw new DaoException("Couldn't find user in users table", e);
        }
    }

    /**
     * Method is being used to get user with given email value from DB's `users` table.
     * If there is no user with given parameters, method returns empty User object.
     * @param email email of potential User. (DB TABLE `users` COLUMN `email`)
     * @return Returns User-object (if user was found). Returns empty User-object (if no user was found)
     * @exception DaoException catches SQLException and throws custom DAO-layer exception
     */

    public User getUserByEmail(String email) {
        User user = new User();
        log.info("Getting user by email from DB ("+ email + ")");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.FIND_USER_BY_EMAIL)) {

            statement.setString(1, email);

            try (ResultSet resSet = statement.executeQuery();) {
                if (resSet.next()) {
                    log.info("User with email  ("+ email + ") was found");

                    user.setId(resSet.getInt("user_id"));
                    user.setName(resSet.getString("name"));
                    user.setSurname(resSet.getString("surname"));
                    user.setEmail(resSet.getString("email"));
                    user.setPassword(resSet.getBytes("password"));
                    user.setSalt(resSet.getBytes("salt"));
                    user.setRole(resSet.getString("role"));
                }
                else {
                    log.warn("No user with email " + email + "was found");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in UserDao.getUserByEmail() " + e.getMessage());
            throw new DaoException("Couldn't find user in users table", e);
        }
        return user;
    }

    /**
     * Method is a part of authorization process. Takes email and ciphered password as parameters,
     * checks if there are coincidences in DB'S `users` table. Returns true or false.
     * @param email email of potential User. (DB TABLE `users` COLUMN `email`)
     * @param password password of potential User. (DB TABLE `users` COLUMN `password`)
     * @return Returns true (if there are coincidences). Returns false (if there are no coincidences).
     * @exception DaoException catches SQLException and throws custom DAO-layer exception.
     */

    public boolean confirmUserExistence(String email, byte[] password) {
        log.info("Confirming user existence ("+ email + ")");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.FIND_USER_BY_EMAIL_PASSWORD)) {
            statement.setString(1, email);
            statement.setBytes(2, password);
            try (ResultSet resSet = statement.executeQuery()) {
                return resSet.next();
            }

        } catch (SQLException e) {
            log.error("SQLException in UserDao.confirmUserExistence() " + e.getMessage());
            throw new DaoException("Couldn't authorize user", e);
        }
    }


    /**
     * Method is being used to add new field to `users` table. All needed parameters for this are given
     * @param user User-object
     * @return Returns true (if field was successfully added to DB). Returns false (if field wasn't added to DB).
     * @exception DaoException catches SQLException and throws custom DAO-layer exception.
     */

    public boolean addUser(User user) {
        log.info("Adding user ("+ user + ") to DB" );

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.INSERT_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setBytes(4, user.getPassword());
            statement.setBytes(5, user.getSalt());
            statement.setString(6, user.getRole());

            statement.executeUpdate();
            log.info("Adding user ("+ user.getEmail() + ") to DB was successfully finished");
            return true;
        } catch (SQLException e) {
            log.error("SQLException in UserDao.addUser() " + e.getMessage());
            throw new DaoException("Couldn't add user to the users table", e);
        }
    }


    /**
     * Method is being used to get`salt` COLUMN from `users`TABLE by given email value.
     * Returns byte[] variable (empty, if no user with given email was found)
     * @param email email of User. (DB TABLE `users` COLUMN `email`)
     * @return Returns true (if there are coincidences). Returns false (if there are no coincidences).
     * @exception DaoException catches SQLException and throws custom DAO-layer exception.
     */

    public byte[] getSalt(String email) {
        byte[] salt;
        log.info("Getting `salt` from DB, where user email is " + email);

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.GET_SALT_BY_EMAIL)) {
            statement.setString(1,email);

            try (ResultSet resSet = statement.executeQuery()) {

                if (resSet.next()) {
                    log.info("`Salt` has successfully obtained");
                    salt = resSet.getBytes("salt");
                }
                else{
                    log.warn("No `salt` found");
                    throw new DaoException("Not `salt` found in DB by given email");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in UserDao.getSalt() " + e.getMessage());
            throw new DaoException("Couldn't get salt from users table", e);
        }
        return salt;
    }

    public boolean updateUser(User user) {
        log.info("Updating user (" + user +")");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.UPDATE_USER)) {


            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setBytes(4, user.getPassword());
            statement.setInt(5, user.getId());

            statement.execute();
            log.info("Updating user ("+ user + ") to DB was successfully finished");
            return true;
        } catch (SQLException e) {
            log.error("SQLException in UserDao.updateUser() " + e.getMessage());
            throw new DaoException("Couldn't update user", e);
        }
    }

    public boolean deleteUser(User user) {
        log.info("Deleting user (" + user +")");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.DELETE_USER)) {

            statement.setInt(1, user.getId());

            statement.execute();
            log.info("Deleting user ("+ user + ") from DB was successfully finished");
            return true;
        } catch (SQLException e) {
            log.error("SQLException in UserDao.deleteUser() " + e.getMessage());
            throw new DaoException("Couldn't delete user", e);
        }
    }

}
