package com.dmdev.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateFormatterTest {
    @Test
    void format() {
    String date = "1234-12-12";

    LocalDate actualResult = LocalDateFormatter.format(date);

    assertThat(actualResult).isEqualTo(LocalDate.of(1234,12,12));
    }
}