package com.company.server.view.input.algorithms.sorting;

import com.company.server.algorithms.categories.array.sorting.StartSorting;
import com.company.server.model.User;
import com.company.server.view.input.algorithms.AlgorithmInput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.server.view.messages.Algorithms.SORTING_ALGORITHM;

public class ManualSortingInput implements AlgorithmInput {

    private static final String ENTER_INPUT_ELEMENTS = "Enter the elements of the array: ";
    private static final String NEXT_ELEMENT = "Next element: ";

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    private final User user;

    public ManualSortingInput(DataInputStream dataInputStream, DataOutputStream dataOutputStream, User user) {

        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        this.user = user;
    }

    @Override
    public void get() throws IOException {

        dataOutputStream.writeUTF(SORTING_ALGORITHM + ". " + ENTER_INPUT_ELEMENTS);

        List<Integer> array = new ArrayList<>();
        String command = dataInputStream.readUTF();

        do {
            array.add(Integer.parseInt(command));
            dataOutputStream.writeUTF(NEXT_ELEMENT);
            command = dataInputStream.readUTF();
        } while (!command.equals(""));

        new StartSorting(array, user).start();
    }
}
