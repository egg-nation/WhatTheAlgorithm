package com.company.server.algorithms.categories.array.searching.methods;

import java.util.ArrayList;
import java.util.List;

public class InterpolationSearch implements SearchMethod {

    private static final String METHOD_NAME = "Interpolation Search";

    private final List<Integer> array;
    private final int elementToBeFound;
    private int result = -1;

    public InterpolationSearch(List<Integer> array, int elementToBeFound) {

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

        int position;
        if (leftIndex <= rightIndex && elementToBeFound >= array.get(leftIndex) && elementToBeFound <= array.get(rightIndex)) {
            position = leftIndex
                    + (((rightIndex - leftIndex) / (array.get(rightIndex) - array.get(leftIndex)))
                    * (elementToBeFound - array.get(leftIndex)));
            if (array.get(position) == elementToBeFound) {
                return position;
            } else if (array.get(position) < elementToBeFound) {
                return algorithm(position + 1, rightIndex);
            } else {
                return algorithm(leftIndex, position - 1);
            }
        }

        return -1;
    }
}
