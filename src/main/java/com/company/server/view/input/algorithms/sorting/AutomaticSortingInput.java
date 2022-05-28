package com.company.server.view.input.algorithms.sorting;

import com.company.server.algorithms.categories.array.sorting.StartSorting;
import com.company.server.model.User;
import com.company.server.view.input.algorithms.AlgorithmInput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.server.view.messages.Algorithms.SORTING_ALGORITHM;

public class AutomaticSortingInput implements AlgorithmInput {

    private static final String ENTER_INPUT_LENGTH = "Enter the number of random elements that you want the array to consist of: ";
    private static final String GENERATED_INPUT_ELEMENTS = "Generated array: ";
    private static final int UPPERBOUND = 200;

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    private final User user;

    public AutomaticSortingInput(DataInputStream dataInputStream, DataOutputStream dataOutputStream, User user) {

        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        this.user = user;
    }

    @Override
    public void get() throws IOException {

        dataOutputStream.writeUTF(SORTING_ALGORITHM + ". " + ENTER_INPUT_LENGTH);

        String command = dataInputStream.readUTF();
        int numberOfElements = Integer.parseInt(command);

        List<Integer> array = new ArrayList<>();
        Random rand = new Random();

        for (int index = 0; index < numberOfElements; ++index) {
            array.add(rand.nextInt(UPPERBOUND));
        }

        dataOutputStream.writeUTF(GENERATED_INPUT_ELEMENTS + array.toString());
        dataInputStream.readUTF();

        new StartSorting(array, user).start();
    }
}
