package com.example.cinema.validator;

import com.example.cinema.model.service.validator.EmailValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailValidatorTest {
    @Test
    void testNullInput() {
        String email = null;
        Assertions.assertFalse(EmailValidator.validate(email));
    }
    @Test
    void testEmptyInput() {
        String email = "";
        Assertions.assertFalse(EmailValidator.validate(email));
    }
    @Test
    void testBadInput() {

        Assertions.assertAll(
                () -> Assertions.assertFalse(EmailValidator.validate("alexeygmail.com")),
                () -> Assertions.assertFalse(EmailValidator.validate("alexey@gmailcom")),
                () -> Assertions.assertFalse(EmailValidator.validate("alexeygmail2com")),
                () -> Assertions.assertFalse(EmailValidator.validate("алексей@gmail.com"))
        );
    }
    @Test
    void testFineInput() {

        Assertions.assertAll(
                () -> Assertions.assertTrue(EmailValidator.validate("alexey@gmail.com")),
                () -> Assertions.assertTrue(EmailValidator.validate("vladimir@ukr.net")),
                () -> Assertions.assertTrue(EmailValidator.validate("svyatoslav121@mail.ru"))
        );
    }
}
