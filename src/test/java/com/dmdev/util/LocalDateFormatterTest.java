package com.dmdev.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocalDateFormatterTest {
    @Test
    void format() {
        String date = "1234-12-12";

        LocalDate actualResult = LocalDateFormatter.format(date);

        assertThat(actualResult).isEqualTo(LocalDate.of(1234,12,12));
    }

    @Test
    void shouldThrowExceptionIfDateInvalid() {
        String date = "1234-12-1222";

        assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format(date));
    }

    @ParameterizedTest
    @MethodSource("getValidationArguments")
    void isValid(String date, boolean expectedResult) {
        boolean actualResult = LocalDateFormatter.isValid(date);

        assertEquals(expectedResult,actualResult);

    }

    static Stream<Arguments> getValidationArguments() {
        return Stream.of(
                Arguments.of("1234-12-12", true),
                Arguments.of("1234-000000", false),
                Arguments.of("12-12-1234", false),
                Arguments.of(null, false),
                Arguments.of("",false)
        );
    }
}