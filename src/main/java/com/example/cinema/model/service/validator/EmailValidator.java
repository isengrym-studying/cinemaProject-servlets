package com.example.cinema.model.service.validator;

import com.example.cinema.model.dao.exceptions.DaoException;

import java.util.regex.Pattern;

/**
 * Class is used for email fields validation
 */
public class EmailValidator {
    public static boolean validate(String email) {
        if(email.length() != 0) {
            Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            return pattern.matcher(email).matches();
        }
        return false;

    }
}
