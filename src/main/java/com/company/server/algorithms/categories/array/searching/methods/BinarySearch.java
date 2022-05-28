package com.company.server.algorithms.categories.array.searching.methods;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch implements SearchMethod {

    private static final String METHOD_NAME = "Binary Search";

    private final List<Integer> array;
    private final int elementToBeFound;
    private int result = -1;

    public BinarySearch(List<Integer> array, int elementToBeFound) {

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
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (array.get(middleIndex) == elementToBeFound) {
                return middleIndex;
            } else if (array.get(middleIndex) > elementToBeFound) {
                return algorithm(leftIndex, middleIndex - 1);
            } else {
                return algorithm(middleIndex + 1, rightIndex);
            }
        }

        return -1;
    }
}
