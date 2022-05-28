package com.company.server.view;

import com.company.server.ioc.IoCModuleService;
import com.company.server.model.User;
import com.company.server.service.interfaces.AlgorithmHistoryService;
import com.company.server.service.interfaces.UserService;
import com.company.server.view.input.algorithms.searching.AutomaticSearchingInput;
import com.company.server.view.input.algorithms.searching.ManualSearchingInput;
import com.company.server.view.input.algorithms.sorting.AutomaticSortingInput;
import com.company.server.view.input.algorithms.sorting.ManualSortingInput;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static com.company.server.view.messages.Algorithms.SEARCHING_ALGORITHM;
import static com.company.server.view.messages.Algorithms.SORTING_ALGORITHM;
import static com.company.server.view.messages.Commands.*;
import static com.company.server.view.messages.Errors.*;
import static com.company.server.view.messages.Intros.*;
import static com.company.server.view.messages.Success.DELETE_HISTORY_SUCCESS;
import static com.company.server.view.messages.Success.DELETE_USER_SUCCESS;

public class AuthenticatedMenu implements Menu {

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    private final User user;
    private final UserService userService;
    private final AlgorithmHistoryService algorithmHistoryService;

    public AuthenticatedMenu(DataInputStream dataInputStream, DataOutputStream dataOutputStream, User user) {

        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        this.user = user;

        Injector injector = Guice.createInjector(new IoCModuleService());
        this.userService = injector.getInstance(UserService.class);
        this.algorithmHistoryService = injector.getInstance(AlgorithmHistoryService.class);
    }

    @Override
    public void start() throws IOException {

        do {
            dataOutputStream.writeUTF(USER_INTRO + user.getUsername() + ": " + AUTHENTICATED_MENU_COMMANDS);
            String command = dataInputStream.readUTF();

            switch (command) {
                case "sort array" -> {
                    dataOutputStream.writeUTF(SORTING_COMMANDS);
                    command = dataInputStream.readUTF();

                    switch (command) {
                        case "view chart" -> {

                            algorithmHistoryService.viewChartForAlgorithm(SORTING_ALGORITHM);
                        }
                        case "start sorting" -> {

                            if (getInputType()) {
                                new ManualSortingInput(dataInputStream, dataOutputStream, user).get();
                            } else {
                                new AutomaticSortingInput(dataInputStream, dataOutputStream, user).get();
                            }
                        }
                        default -> {

                            dataOutputStream.writeUTF(INVALID_COMMAND_ERROR);
                            dataInputStream.readUTF();
                        }
                    }
                }
                case "search array" -> {
                    dataOutputStream.writeUTF(SEARCHING_COMMANDS);
                    command = dataInputStream.readUTF();

                    switch (command) {
                        case "view chart" -> {

                            algorithmHistoryService.viewChartForAlgorithm(SEARCHING_ALGORITHM);
                        }
                        case "start searching" -> {

                            if (getInputType()) {
                                new ManualSearchingInput(dataInputStream, dataOutputStream, user).get();
                            } else {
                                new AutomaticSearchingInput(dataInputStream, dataOutputStream, user).get();
                            }
                        }
                        default -> {

                            dataOutputStream.writeUTF(INVALID_COMMAND_ERROR);
                            dataInputStream.readUTF();
                        }
                    }
                }
                case "view history" -> {

                    dataOutputStream.writeUTF(algorithmHistoryService.getHistory().toString());
                }
                case "delete history" -> {

                    algorithmHistoryService.deleteHistory(user.getId());

                    dataOutputStream.writeUTF(DELETE_HISTORY_SUCCESS);
                    dataInputStream.readUTF();
                }
                case "delete user" -> {

                    algorithmHistoryService.deleteHistory(user.getId());
                    userService.deleteUser(user.getId());

                    dataOutputStream.writeUTF(DELETE_USER_SUCCESS);
                    dataInputStream.readUTF();
                    return;
                }
                case "exit" -> {

                    return;
                }
                default -> {

                    dataOutputStream.writeUTF(INVALID_COMMAND_ERROR);
                    dataInputStream.readUTF();
                }
            }
        } while (true);
    }

    private boolean getInputType() throws IOException {

        dataOutputStream.writeUTF(INPUT_TYPE_COMMANDS);
        String command = dataInputStream.readUTF();

        do {
            switch (command) {
                case "manual input" -> {
                    return true;
                }
                case "automatic input" -> {
                    return false;
                }
                default -> {
                    dataOutputStream.writeUTF(INVALID_COMMAND_ERROR);
                    dataInputStream.readUTF();
                }
            }
            dataOutputStream.writeUTF(INPUT_TYPE_COMMANDS);
            command = dataInputStream.readUTF();
        } while (true);

    }
}
