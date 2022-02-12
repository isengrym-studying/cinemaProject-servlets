package com.example.cinema.model.service.validator;

import java.util.regex.Pattern;

/**
 * Class is used for name and surname fields validation
 */
public class NameValidator {
    public static boolean validate(String name) {
        if(name != null && name.length() != 0) {
            Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Яєїё']+$");
            return pattern.matcher(name).matches();
        }
        return false;
    }
}
