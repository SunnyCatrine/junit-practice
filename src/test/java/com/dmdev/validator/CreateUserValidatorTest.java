package com.dmdev.validator;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserValidatorTest {

    CreateUserValidator validator = CreateUserValidator.getInstance();

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

}