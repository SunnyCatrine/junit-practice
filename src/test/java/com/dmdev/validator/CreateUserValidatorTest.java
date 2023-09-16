package com.dmdev.validator;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    void invalidGender() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .birthday("1234-12-12")
                .email("123@qwe.com")
                .gender("qw")
                .name("Mesut")
                .password("mesosh")
                .role(Role.USER.name())
                .build();

        ValidationResult actualResult = validator.validate(createUserDto);

        assertThat(actualResult.getErrors()).hasSize(1);
        assertThat(actualResult.getErrors().get(0).getCode()).isEqualTo("invalid.gender");
    }

    @Test
    void invalidRole() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .birthday("1234-12-12")
                .email("123@qwe.com")
                .gender(Gender.MALE.name())
                .name("Mesut")
                .password("mesosh")
                .role("qw")
                .build();

        ValidationResult actualResult = validator.validate(createUserDto);

        assertThat(actualResult.getErrors()).hasSize(1);
        assertThat(actualResult.getErrors().get(0).getCode()).isEqualTo("invalid.role");
    }

    @Test
    void invalidBirthdayGenderRole() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .birthday("1234-12-12999")
                .email("123@qwe.com")
                .gender("qwr")
                .name("Mesut")
                .password("mesosh")
                .role("qw")
                .build();

        ValidationResult actualResult = validator.validate(createUserDto);

        assertThat(actualResult.getErrors()).hasSize(3);
        List<String> errorsCodes = actualResult.getErrors().stream().map(Error::getCode).collect(Collectors.toList());
        assertThat(errorsCodes).contains("invalid.role", "invalid.birthday", "invalid.gender");
    }

}