package com.company.server.view;

import com.company.server.ioc.IoCModuleService;
import com.company.server.model.User;
import com.company.server.service.interfaces.UserService;
import com.company.server.view.input.menu.GetDirectInput;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

import static com.company.server.view.messages.Commands.*;
import static com.company.server.view.messages.Intros.*;
import static com.company.server.view.messages.Success.*;
import static com.company.server.view.messages.Errors.*;

public class MainMenu implements Menu {

    private final Socket client;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    private final GetDirectInput getDirectInput;
    private final UserService userService;

    public MainMenu(Socket client, DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {

        this.client = client;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        this.getDirectInput = new GetDirectInput(dataInputStream, dataOutputStream);

        Injector injector = Guice.createInjector(new IoCModuleService());
        this.userService = injector.getInstance(UserService.class);
    }

    @Override
    public void start() throws IOException {

        do {
            dataOutputStream.writeUTF(MAIN_MENU_INTRO + ": " + MAIN_MENU_COMMANDS);
            String command = dataInputStream.readUTF();

            switch (command) {
                case "login" -> {

                    String username = getDirectInput.getUsername();
                    String passwordHash = getDirectInput.getPasswordHash();

                    User foundUser = userService.login(
                            username,
                            passwordHash
                    );

                    if (Objects.nonNull(foundUser)) {
                        dataOutputStream.writeUTF(LOGIN_SUCCESS);
                        dataInputStream.readUTF();
                    } else {
                        dataOutputStream.writeUTF(LOGIN_ERROR);
                        dataInputStream.readUTF();
                        break;
                    }

                    new AuthenticatedMenu(dataInputStream, dataOutputStream, foundUser).start();
                }
                case "register" -> {

                    String firstName = getDirectInput.getFirstName();
                    String lastName = getDirectInput.getLastName();
                    String username = getDirectInput.getPossibleUsername();
                    String emailAddress = getDirectInput.getPossibleEmailAddress();
                    String passwordHash = getDirectInput.getPossiblePasswordHash();

                    User newUser = userService.createUser(
                            firstName,
                            lastName,
                            username,
                            emailAddress,
                            passwordHash
                    );

                    if (Objects.nonNull(newUser)) {
                        dataOutputStream.writeUTF(REGISTER_SUCCESS);
                        dataInputStream.readUTF();
                    } else {
                        dataOutputStream.writeUTF(REGISTER_ERROR);
                        dataInputStream.readUTF();
                        break;
                    }

                    new AuthenticatedMenu(dataInputStream, dataOutputStream, newUser).start();
                }
                case "exit" -> {

                    System.out.println("Client " + client + " sent exit command...");
                    System.out.println("Closing this connection.");
                    this.client.close();

                    System.out.println("Connection closed");
                    return;
                }
                default -> {

                    dataOutputStream.writeUTF(INVALID_COMMAND_ERROR);
                    dataInputStream.readUTF();
                }
            }
        } while (true);
    }
}
