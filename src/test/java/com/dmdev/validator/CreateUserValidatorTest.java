package com.dmdev.validator;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateUserValidatorTest {

    private final CreateUserValidator validator = CreateUserValidator.getInstance();

    @Test
    void shouldPassValidation() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .birthday("1234-12-12")
                .email("123@qwe.com")
                .gender(Gender.MALE.name())
                .name("Mesut")
                .password("mesosh")
                .role(Role.USER.name())
                .build();

        ValidationResult actualResult = validator.validate(createUserDto);

        assertFalse(actualResult.hasErrors());
    }

    @Test
    void invalidBirthday() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .birthday("1234-12-1212212")
                .email("123@qwe.com")
                .gender(Gender.MALE.name())
                .name("Mesut")
                .password("mesosh")
                .role(Role.USER.name())
                .build();

        ValidationResult actualResult = validator.validate(createUserDto);

        assertThat(actualResult.getErrors()).hasSize(1);
        assertThat(actualResult.getErrors().get(0).getCode()).isEqualTo("invalid.birthday");
    }

}