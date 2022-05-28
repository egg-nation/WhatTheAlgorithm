package com.company.server.service.interfaces;

import com.company.server.model.User;

public interface UserService extends Service {

    User createUser(String firstName, String lastName, String username, String emailAddress, String passwordHash);

    User login(String usernameOrEmailAddress, String passwordHash);

    void deleteUser(int userId);
}
