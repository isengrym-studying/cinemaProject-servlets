package com.example.cinema.model.dao;

import com.example.cinema.model.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static UserDao userDao;

    public static synchronized UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    private UserDao() {
    }

    public boolean findUserByEmail(String email) {
        ResultSet resSet;

        try (PreparedStatement statement = ConnectionPool
                .getInstance().getConnection().prepareStatement(SQLQuery.UserQuery.FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            resSet = statement.executeQuery();

            return resSet.next();
        } catch (SQLException e) {
            throw new DaoException("Couldn't find user in users table", e);
        }
    }

    public boolean confirmUserExistence(String email, byte[] password) {
        ResultSet resSet;

        try (PreparedStatement statement = ConnectionPool
                .getInstance().getConnection().prepareStatement(SQLQuery.UserQuery.FIND_USER_BY_EMAIL_PASSWORD)) {
            statement.setString(1, email);
            statement.setBytes(2, password);
            resSet = statement.executeQuery();

            return resSet.next();
        } catch (SQLException e) {
            throw new DaoException("Couldn't authorize user", e);
        }
    }

    public boolean addUser(String name, String surname, String email, byte[] password, byte[] salt, String role) {

        try (PreparedStatement statement = ConnectionPool
                .getInstance().getConnection().prepareStatement(SQLQuery.UserQuery.INSERT_USER)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setBytes(4, password);
            statement.setBytes(5, salt);
            statement.setString(6, role);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Couldn't add user to the users table", e);
        }
    }

    public byte[] getSalt(String email) {
        ResultSet resSet;

        try(PreparedStatement statement = ConnectionPool
                .getInstance().getConnection().prepareStatement(SQLQuery.UserQuery.GET_SALT_BY_EMAIL)) {
            statement.setString(1,email);
            resSet = statement.executeQuery();
            if (resSet.next()) return resSet.getBytes("salt");
        } catch (SQLException e) {
            throw new DaoException("Couldn't get salt from users table", e);
        }
        return null;
    }

}
