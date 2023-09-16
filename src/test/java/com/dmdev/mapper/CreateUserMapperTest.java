package com.dmdev.mapper;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserMapperTest {

    private final CreateUserMapper mapper = CreateUserMapper.getInstance();

    @Test
    void map() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .birthday("1234-12-12")
                .email("123@qwe.com")
                .gender(Gender.MALE.name())
                .name("Mesut")
                .password("mesosh")
                .role(Role.USER.name())
                .build();

        User actualResult = mapper.map(createUserDto);

        User expectedResult = User.builder()
                .birthday(LocalDate.of(1234,12,12))
                .email("123@qwe.com")
                .gender(Gender.MALE)
                .name("Mesut")
                .password("mesosh")
                .role(Role.USER)
                .build();

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }
}