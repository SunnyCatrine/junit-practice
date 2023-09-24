package com.dmdev.dao;

import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        userDao.save(getUser("email1@gmail.com"));
        User savedUser2 = userDao.save(getUser("email2@gmail.com"));
        userDao.save(getUser("email3@gmail.com"));

        Optional<User> actualResult = userDao.findById(savedUser2.getId());

        assertThat(actualResult).isPresent();
        assertThat(actualResult).get().isEqualTo(savedUser2);
    }

    @Test
    void save() {
        User user = getUser("email@gmail.com");

        User actualResult = userDao.save(user);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getId()).isNotNull();
    }

    @Test
    void findByEmailAndPassword() {
        userDao.save(getUser("email1@gmail.com"));
        User savedUser2 = userDao.save(getUser("email2@gmail.com"));
        userDao.save(getUser("email3@gmail.com"));

        Optional<User> actualResult = userDao.findByEmailAndPassword(savedUser2.getEmail(), savedUser2.getPassword());

        assertThat(actualResult).isPresent();
        assertThat(actualResult).get().isEqualTo(savedUser2);
    }

    @Test
    void delete() {
        userDao.save(getUser("email1@gmail.com"));
        User savedUser2 = userDao.save(getUser("email2@gmail.com"));
        userDao.save(getUser("email3@gmail.com"));

        boolean actualResult = userDao.delete(savedUser2.getId());

        assertTrue(actualResult);
    }

    @Test
    void update() {
        userDao.save(getUser("email1@gmail.com"));
        User user2 = getUser("email2@gmail.com");
        User savedUser2 = userDao.save(user2);
        userDao.save(getUser("email3@gmail.com"));

        user2.setName("Arslan");
        user2.setPassword("098");

        userDao.update(user2);

        Optional<User> actualResult = userDao.findById(savedUser2.getId());

        assertThat(actualResult).isPresent();
        assertThat(actualResult).get().isEqualTo(user2);


    }

    private User getUser(String email) {
        return User.builder()
                .birthday(LocalDate.of(1234,12,12))
                .email(email)
                .gender(Gender.MALE)
                .name("Mesut")
                .role(Role.USER)
                .password("qwe")
                .build();
    }
}