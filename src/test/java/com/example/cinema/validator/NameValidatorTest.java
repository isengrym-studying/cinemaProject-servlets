package com.example.cinema.validator;

import com.example.cinema.service.validator.NameValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameValidatorTest {
    @Test
    void testNullInput() {
        String name = null;
        Assertions.assertFalse(NameValidator.validate(name));
    }
    @Test
    void testEmptyInput() {
        String name = "";
        Assertions.assertFalse(NameValidator.validate(name));
    }
    @Test
    void testBadInput() {

        Assertions.assertAll(
                () -> Assertions.assertFalse(NameValidator.validate("Алексе1")),
                () -> Assertions.assertFalse(NameValidator.validate("V1adimir")),
                () -> Assertions.assertFalse(NameValidator.validate("_Sanyok_")),
                () -> Assertions.assertFalse(NameValidator.validate("V@dim"))
        );
    }
    @Test
    void testFineInput() {

        Assertions.assertAll(
                () -> Assertions.assertTrue(NameValidator.validate("алексей")),
                () -> Assertions.assertTrue(NameValidator.validate("Владимир")),
                () -> Assertions.assertTrue(NameValidator.validate("aleksandr")),
                () -> Assertions.assertTrue(NameValidator.validate("Petr"))
        );
    }
}
