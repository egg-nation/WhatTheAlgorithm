package com.company.server.view.input.algorithms.searching;

import com.company.server.algorithms.categories.array.searching.StartSearching;
import com.company.server.model.User;
import com.company.server.view.input.algorithms.AlgorithmInput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.server.view.messages.Algorithms.SEARCHING_ALGORITHM;

public class ManualSearchingInput implements AlgorithmInput {

    private static final String ENTER_INPUT_ELEMENTS = "Enter the elements of the array: ";
    private static final String NEXT_ELEMENT = "Next element: ";
    private static final String ENTER_ELEMENT_TO_BE_FOUND = "Enter the value to be found: ";

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    private final User user;

    public ManualSearchingInput(DataInputStream dataInputStream, DataOutputStream dataOutputStream, User user) {

        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        this.user = user;
    }

    @Override
    public void get() throws IOException {

        dataOutputStream.writeUTF(SEARCHING_ALGORITHM + ". " + ENTER_INPUT_ELEMENTS);

        List<Integer> array = new ArrayList<>();
        String command = dataInputStream.readUTF();

        do {
            array.add(Integer.parseInt(command));
            dataOutputStream.writeUTF(NEXT_ELEMENT);
            command = dataInputStream.readUTF();
        } while (!command.equals(""));

        dataOutputStream.writeUTF(ENTER_ELEMENT_TO_BE_FOUND);
        int elementToBeFound;
        command = dataInputStream.readUTF();
        elementToBeFound = Integer.parseInt(command);

        new StartSearching(array, elementToBeFound, user).start();
    }
}
