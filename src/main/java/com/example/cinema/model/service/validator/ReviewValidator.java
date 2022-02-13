package com.example.cinema.model.service.validator;

import java.util.regex.Pattern;

public class ReviewValidator {
    public static boolean validate(String reviewText) {
        if (reviewText != null
                && reviewText.length()>=10
                && reviewText.length()<=250) return true;
        return false;
    }
}
