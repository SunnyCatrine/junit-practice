package com.dmdev.dao;

import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoIT extends IntegrationTestBase {

    private UserDao userDao = UserDao.getInstance();

    @Test
    void findAll() {
        User savedUser1 = userDao.save(getUser("email1@gmail.com"));
        User savedUser2 = userDao.save(getUser("email2@gmail.com"));
        User savedUser3 = userDao.save(getUser("email3@gmail.com"));

        List<User> actualResult = userDao.findAll();
        List<Integer> actualIds = actualResult.stream()
                .map(User::getId)
                .collect(Collectors.toList());

        assertThat(actualResult).size().isEqualTo(3);
        assertThat(actualIds).contains(savedUser1.getId(),savedUser2.getId(),savedUser3.getId());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void findByEmailAndPassword() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    private User getUser(String email) {
        return User.builder()
                .birthday(LocalDate.of(1234,12,12))
                .email(email)
                .gender(Gender.MALE)
                .name("Mesut")
                .role(Role.USER)
                .id(10)
                .password("qwe")
                .build();
    }
}