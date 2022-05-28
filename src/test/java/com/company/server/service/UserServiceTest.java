package com.company.server.service;

import com.company.server.ioc.IoCModuleService;
import com.company.server.model.User;
import com.company.server.repository.repositories.interfaces.UserRepository;
import com.company.server.service.interfaces.UserService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    protected Injector injector = Guice.createInjector(new IoCModuleService());
    @InjectMocks
    UserService userService = injector.getInstance(UserService.class);

    @BeforeEach
    public void init() {

        MockitoAnnotations.openMocks(this);
    }

    UserServiceTest() {

    }

    @Test
    void whenCreateUserWithNonexistentUsernameAndNonexistentEmailAddress() {

        // given
        User user = new User(
                1,
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );
        when(userRepository.insert(
                any(), any(), any(), any(), any()
        )).thenReturn(user);

        // when
        User returnedUser = userService.createUser(
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );

        // then
        assertSame(user, returnedUser);
    }

    @Test
    void whenCreateUserWithExistentUsername() {

        // given
        User user = new User(
                1,
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );
        when(userRepository.findByUsername("elisa_giurgea")).thenReturn(user);

        // when
        User returnedUser = userService.createUser(
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );

        // then
        assertNull(returnedUser);
    }

    @Test
    void whenCreateUserWithNonexistentUsernameAndExistentEmailAddress() {

        // given
        User user = new User(
                1,
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );
        when(userRepository.findByUsername("elisa.giurgea@mambu.com")).thenReturn(user);

        // when
        User returnedUser = userService.createUser(
                "Elisa1",
                "Giurgea1",
                "elisa_giurgea1",
                "elisa.giurgea@mambu.com",
                "hash"
        );

        // then
        assertNull(returnedUser);
    }

    @Test
    void whenLoginWithCorrectUsernameAndCorrectPassword() {

        // given
        User user = new User(
                1,
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );
        when(userRepository.findByUsername("elisa_giurgea")).thenReturn(user);

        // when
        User returnedUser = userService.login("elisa_giurgea", "hash");

        // then
        assertSame(user, returnedUser);
    }

    @Test
    void whenLoginWithCorrectEmailAddressAndCorrectPassword() {

        // given
        User user = new User(
                1,
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );
        when(userRepository.findByEmailAddress("elisa.giurgea@mambu.com")).thenReturn(user);

        // when
        User returnedUser = userService.login("elisa.giurgea@mambu.com", "hash");

        // then
        assertSame(user, returnedUser);
    }

    @Test
    void whenLoginWithIncorrectUsername() {

        // given
        when(userRepository.findByUsername("elisa_giurgea1")).thenReturn(null);

        // when
        User returnedUser = userService.login("elisa_giurgea1", "hash");

        // then
        assertNull(returnedUser);
    }

    @Test
    void whenLoginWithIncorrectEmailAddress() {

        // given
        when(userRepository.findByEmailAddress("elisa.giurgea@mambucom")).thenReturn(null);

        // when
        User returnedUser = userService.login("elisa.giurgea@mambucom", "hash");

        // then
        assertNull(returnedUser);
    }

    @Test
    void whenLoginWithCorrectUsernameAndIncorrectPassword() {

        // given
        User user = new User(
                1,
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );
        when(userRepository.findByUsername("elisa_giurgea")).thenReturn(user);

        // when
        User returnedUser = userService.login("elisa_giurgea", "hash1");

        // then
        assertNull(returnedUser);
    }

    @Test
    void whenLoginWithCorrectEmailAddressAndIncorrectPassword() {

        // given
        User user = new User(
                1,
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );
        when(userRepository.findByEmailAddress("elisa.giurgea@mambu.com")).thenReturn(user);

        // when
        User returnedUser = userService.login("elisa.giurgea@mambu.com", "hash1");

        // then
        assertNull(returnedUser);
    }

    @Test
    void deleteUser() {

        // given
        userRepository.insert(
                "Elisa",
                "Giurgea",
                "elisa_giurgea",
                "elisa.giurgea@mambu.com",
                "hash"
        );

        // when
        userService.deleteUser(1);

        // then
        assertNull(userRepository.findByUsername("elisa_giurgea"));
    }
}