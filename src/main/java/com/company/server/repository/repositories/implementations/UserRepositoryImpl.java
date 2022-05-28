package com.company.server.repository.repositories.implementations;

import com.company.server.model.User;
import com.company.server.repository.repositories.interfaces.UserRepository;
import com.company.server.repository.session.DatabaseConnection;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Jdbi jdbi;
    private List<User> users;

    public UserRepositoryImpl() {

        this.jdbi = DatabaseConnection.getInstance().getConnection();

        update();
    }

    @Override
    public void update() {

        users = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM users")
                .mapToBean(User.class)
                .list());
    }

    @Override
    public User findByUsername(String username) {

        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByEmailAddress(String emailAddress) {

        return users.stream()
                .filter(user -> user.getEmailAddress().equals(emailAddress))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User insert(String firstName, String lastName, String username, String emailAddress, String passwordHash) {

        jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO users (first_name, last_name, username, email_address, password_hash) VALUES (:first_name, :last_name, :username, :email_address, :password_hash)")
                .bind("first_name", firstName)
                .bind("last_name", lastName)
                .bind("username", username)
                .bind("email_address", emailAddress)
                .bind("password_hash", passwordHash)
                .execute()
        );

        update();

        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(int id) {

        jdbi.withHandle(handle -> handle.createUpdate("DELETE FROM users WHERE (id = :id)")
                .bind("id", id)
                .execute()
        );

        update();
    }
}
