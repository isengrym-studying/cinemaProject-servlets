package com.example.cinema.service.validator;

import java.util.regex.Pattern;

/**
 * Class is used for password fields validation
 */
public class PasswordValidator {
    public static boolean validate(String password) {
        if (password != null && password.length() != 0) {
            Pattern pattern = Pattern.compile("^(?=.*?[0-9]).{8,}$");
            return pattern.matcher(password).matches();
        }
        return false;
    }
}
