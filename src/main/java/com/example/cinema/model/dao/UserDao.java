package com.example.cinema.model.dao;

import com.example.cinema.model.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static Logger log = Logger.getLogger(UserDao.class);
    private static UserDao userDao;

    public static synchronized UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    private UserDao() {
    }

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

    public boolean addUser(String name, String surname, String email, byte[] password, byte[] salt, String role) {
        log.info("Adding user with email "+ email + " to DB" );

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.INSERT_USER)
) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setBytes(4, password);
            statement.setBytes(5, salt);
            statement.setString(6, role);

            statement.executeUpdate();
            log.info("Adding user with email "+ email + " to DB was successfully finished");
            return true;
        } catch (SQLException e) {
            log.error("SQLException in UserDao.addUser() " + e.getMessage());
            throw new DaoException("Couldn't add user to the users table", e);
        }
    }

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

}
