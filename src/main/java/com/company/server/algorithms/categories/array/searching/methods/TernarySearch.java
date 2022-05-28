package com.company.server.algorithms.categories.array.searching.methods;

import java.util.ArrayList;
import java.util.List;

public class TernarySearch implements SearchMethod {

    private static final String METHOD_NAME = "Ternary Search";

    private final List<Integer> array;
    private final int elementToBeFound;
    private int result = -1;

    public TernarySearch(List<Integer> array, int elementToBeFound) {

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

        this.result = algorithm(0, array.size() - 1);
    }

    private int algorithm(int leftIndex, int rightIndex) {

        if (rightIndex >= leftIndex) {
            int middleIndex1 = leftIndex + (rightIndex - leftIndex) / 3;
            int middleIndex2 = rightIndex - (rightIndex - leftIndex) / 3;

            if (array.get(middleIndex1) == elementToBeFound) {
                return middleIndex1;
            } else if (array.get(middleIndex2) == elementToBeFound) {
                return middleIndex2;
            }

            if (array.get(middleIndex1) > elementToBeFound) {
                return algorithm(leftIndex, middleIndex1 - 1);
            } else if (array.get(middleIndex2) < elementToBeFound) {
                return algorithm(middleIndex2 + 1, rightIndex);
            } else {
                return algorithm(middleIndex1 + 1, middleIndex2 - 1);
            }
        }

        return -1;
    }
}
