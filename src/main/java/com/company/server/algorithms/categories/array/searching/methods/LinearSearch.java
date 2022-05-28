package com.company.server.algorithms.categories.array.searching.methods;

import java.util.ArrayList;
import java.util.List;

public class LinearSearch implements SearchMethod {

    private static final String METHOD_NAME = "Linear Search";

    private final List<Integer> array;
    private final int elementToBeFound;
    private int result = -1;

    public LinearSearch(List<Integer> array, int elementToBeFound) {

        this.array = new ArrayList<>(array);
        this.elementToBeFound = elementToBeFound;
    }

    @Override
    public int getResult() {

        return result;
    }

    @Override
    public String getMethodName() {

        return METHOD_NAME;
    }

    @Override
    public void startAlgorithm() {

        this.result = algorithm();
    }

    private int algorithm() {

        int arrayLenght = array.size();
        for (int index = 0; index < arrayLenght; ++index) {
            if (array.get(index) == elementToBeFound) {
                return index;
            }
        }

        return -1;
    }
}
