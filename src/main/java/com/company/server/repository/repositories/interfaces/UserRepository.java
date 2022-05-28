package com.company.server.repository.repositories.interfaces;

import com.company.server.model.User;

public interface UserRepository extends Repository {

    User findByUsername(String username);

    User findByEmailAddress(String emailAddress);

    User insert(String firstName, String lastName, String username, String emailAddress, String passwordHash);
}
