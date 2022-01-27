package com.example.cinema.controller.logic;
import com.example.cinema.model.dao.UserDao;

public class LoginLogic {
    static UserDao service = UserDao.getInstance();
    public static boolean authorize(String email, String password) { return service.authorize(email, password); }
}
