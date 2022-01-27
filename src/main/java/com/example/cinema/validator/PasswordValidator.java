package com.example.cinema.validator;

import java.util.regex.Pattern;

public class PasswordValidator {
    public static boolean validate(String password) {
        Pattern pattern = Pattern.compile("^(?=.*?[0-9]).{8,}$");
        return pattern.matcher(password).matches();
    }
}
