package com.company.server.service.implementations;

import com.company.server.ioc.IoCModuleRepository;
import com.company.server.model.User;
import com.company.server.repository.repositories.interfaces.UserRepository;
import com.company.server.service.interfaces.UserService;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Objects;

public class UserServiceImpl implements UserService {

    public UserRepository userRepository;

    public UserServiceImpl() {

        Injector injector = Guice.createInjector(new IoCModuleRepository());
        this.userRepository = injector.getInstance(UserRepository.class);
    }

    @Override
    public User createUser(String firstName, String lastName, String username, String emailAddress, String passwordHash) {

        return (Objects.isNull(userRepository.findByUsername(username))
                && Objects.isNull(userRepository.findByEmailAddress(emailAddress))) ?
                userRepository.insert(firstName, lastName, username, emailAddress, passwordHash) : null;
    }

    @Override
    public User login(String usernameOrEmailAddress, String passwordHash) {

        User foundUser = userRepository.findByUsername(usernameOrEmailAddress);

        if (Objects.isNull(foundUser)) {
            foundUser = userRepository.findByEmailAddress(usernameOrEmailAddress);
            if (Objects.isNull(foundUser)) {
                return null;
            }
        }

        return (foundUser.getPasswordHash().trim().equals(passwordHash.trim())) ? foundUser : null;
    }

    @Override
    public void deleteUser(int userId) {

        userRepository.delete(userId);
    }
}
