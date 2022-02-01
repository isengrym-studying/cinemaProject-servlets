package com.example.cinema.model.service.validator;

import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean validate(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        return pattern.matcher(email).matches();
    }
}
