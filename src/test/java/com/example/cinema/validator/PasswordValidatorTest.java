package com.example.cinema.validator;

import com.example.cinema.service.validator.PasswordValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {
    @Test
    void testNullInput() {
        String password= null;
        Assertions.assertFalse(PasswordValidator.validate(password));
    }
    @Test
    void testEmptyInput() {
        String password = "";
        Assertions.assertFalse(PasswordValidator.validate(password));
    }
    @Test
    void testBadInput() {

        Assertions.assertAll(
                () -> Assertions.assertFalse(PasswordValidator.validate("fhr3g7")),
                () -> Assertions.assertFalse(PasswordValidator.validate("fhktgbcdwvht")),
                () -> Assertions.assertFalse(PasswordValidator.validate("fdg@m^!@"))
        );
    }
    @Test
    void testFineInput() {

        Assertions.assertAll(
                () -> Assertions.assertTrue(PasswordValidator.validate("nk34qwdnkl12jn6")),
                () -> Assertions.assertTrue(PasswordValidator.validate("jf8g3k6g")),
                () -> Assertions.assertTrue(PasswordValidator.validate("$gy4@xf|&"))
        );
    }
}
