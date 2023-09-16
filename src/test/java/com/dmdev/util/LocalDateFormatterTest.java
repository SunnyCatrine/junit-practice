package com.dmdev.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
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
}