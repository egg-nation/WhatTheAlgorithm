package com.company.server.view.input.algorithms.searching;

import com.company.server.algorithms.categories.array.searching.StartSearching;
import com.company.server.model.User;
import com.company.server.view.input.algorithms.AlgorithmInput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

import static com.company.server.view.messages.Algorithms.SEARCHING_ALGORITHM;

public class AutomaticSearchingInput implements AlgorithmInput {

    private static final String ENTER_INPUT_LENGTH = "Enter the number of random elements that you want the array to consist of: ";
    private static final String GENERATED_INPUT_ELEMENTS = "Generated array: ";
    private static final String GENERATED_ELEMENT_TO_BE_FOUND = "Element to be found: ";

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    private final User user;

    public AutomaticSearchingInput(DataInputStream dataInputStream, DataOutputStream dataOutputStream, User user) {

        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        this.user = user;
    }

    @Override
    public void get() throws IOException {

        dataOutputStream.writeUTF(SEARCHING_ALGORITHM + ". " + ENTER_INPUT_LENGTH);

        String command = dataInputStream.readUTF();
        int numberOfElements = Integer.parseInt(command);

        List<Integer> array = new ArrayList<>();
        Random rand = new Random();
        int upperbound = 200;

        for (int index = 0; index < numberOfElements; ++index) {
            array.add(rand.nextInt(upperbound));
        }

        Collections.sort(array);
        int elementToBeFound = array.get(rand.nextInt(numberOfElements));

        dataOutputStream.writeUTF(GENERATED_INPUT_ELEMENTS + array.toString() + "\n" + GENERATED_ELEMENT_TO_BE_FOUND + elementToBeFound);
        dataInputStream.readUTF();

        new StartSearching(array, elementToBeFound, user).start();
    }
}
