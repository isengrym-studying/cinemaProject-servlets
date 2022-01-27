package com.example.cinema.model.dao;

import com.example.cinema.model.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private DaoFactory daoFactory = DaoFactory.getInstance("CinemaProject");
    private static UserDao userDao;

    public static synchronized UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public User getUserByEmail(String email) {
        ResultSet resSet;
        User user = new User();

        try (PreparedStatement statement = daoFactory
                .getDbConnection().prepareStatement(SQLQuery.UserQuery.FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            resSet = statement.executeQuery();
            while (resSet.next()) {
                user.setName(resSet.getString("name"));
                user.setSurname(resSet.getString("surname"));
                user.setEmail(resSet.getString("email"));
                user.setPassword(resSet.getString("password"));
                user.setRole(resSet.getString("role"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean authorize(String email, String password) {
        ResultSet resSet;

        try (PreparedStatement statement = daoFactory
                .getDbConnection().prepareStatement(SQLQuery.UserQuery.FIND_USER_BY_EMAIL_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            resSet = statement.executeQuery();

            return resSet.next();
        } catch (SQLException e) {
            throw new DaoException("Couldn't authorize user", e);
        }
    }


    public boolean register(String name, String surname, String email, String password, String role) {

        try (PreparedStatement statement = daoFactory
                .getDbConnection().prepareStatement(SQLQuery.UserQuery.INSERT_USER)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, role);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException("Couldn't registrate user", e);
        }
    }

    public boolean checkUserExistence(String email){
        ResultSet resSet;

        try (PreparedStatement statement = daoFactory
                .getDbConnection().prepareStatement(SQLQuery.UserQuery.FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            resSet = statement.executeQuery();
            return resSet.next();

        } catch (SQLException e) {
            throw new DaoException("Couldn't check users existence", e);
        }
    }
}
