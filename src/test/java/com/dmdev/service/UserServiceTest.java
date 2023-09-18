package com.dmdev.service;

import com.dmdev.dao.UserDao;
import com.dmdev.dto.CreateUserDto;
import com.dmdev.dto.UserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.mapper.CreateUserMapper;
import com.dmdev.mapper.UserMapper;
import com.dmdev.validator.CreateUserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private CreateUserValidator createUserValidator;
    @Mock
    private UserDao userDao;
    @Mock
    private CreateUserMapper createUserMapper;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @Test
    void loginSuccess() {
        User user = getUser();
        UserDto userDto = getUserDto();

        doReturn(Optional.of(user)).when(userDao).findByEmailAndPassword(user.getEmail(),user.getPassword());
        doReturn(userDto).when(userMapper).map(user);

        Optional<UserDto> actualResult = userService.login(user.getEmail(), user.getPassword());

        assertThat(actualResult).isPresent();
        assertThat(actualResult).hasValue(userDto);
    }

    @Test
    void loginFailed() {
        doReturn(Optional.empty()).when(userDao).findByEmailAndPassword(any(),any());

        Optional<UserDto> actualResult = userService.login("12", "yu");

        assertThat(actualResult).isEmpty();
        verifyNoInteractions(userMapper);
    }

    private UserDto getUserDto() {
        return UserDto.builder()
                .birthday(LocalDate.of(1234,12,12))
                .email("123@qwe.com")
                .gender(Gender.MALE)
                .name("Mesut")
                .role(Role.USER)
                .id(10)
                .build();
    }

    private User getUser() {
        return User.builder()
                .birthday(LocalDate.of(1234,12,12))
                .email("123@qwe.com")
                .gender(Gender.MALE)
                .name("Mesut")
                .role(Role.USER)
                .id(10)
                .password("qwe")
                .build();
    }
}