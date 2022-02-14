package com.example.cinema.controller;

import com.example.cinema.model.entity.Role;
import com.example.cinema.model.entity.User;

public class AccessStatus {
    public static boolean isAdmin (User user){
        if (user != null) return user.getRole().equals(Role.ADMIN.getString());
        else return false;
    }
    public static boolean isUser (User user){
        if (user != null) return user.getRole().equals(Role.USER.getString()) || user.getRole().equals(Role.ADMIN.getString());
        else return false;
    }
}
