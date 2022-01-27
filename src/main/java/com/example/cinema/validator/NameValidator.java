package com.example.cinema.validator;

import java.util.regex.Pattern;

public class NameValidator {
    public static boolean validate(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Яєї']+$");
        return pattern.matcher(name).matches();
    }
}
