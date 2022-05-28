package com.company.server.algorithms.categories.array.sorting.methods;

public class SelectionSort implements SortMethod {

    private static final String METHOD_NAME = "Selection Sort";

    private final int[] array;
    private final int size;

    public SelectionSort(int[] array) {

        this.array = array.clone();
        this.size = array.length;
    }

    @Override
    public int[] getResult() {

        return array;
    }

    @Override
    public String getMethodName() {

        return METHOD_NAME;
    }

    @Override
    public void startAlgorithm() {

        algorithm();
    }

    private void algorithm() {

        for (int indexBoundary = 0; indexBoundary < size - 1; ++indexBoundary) {
            int minValueUnsorted = indexBoundary;

            for (int indexUnsorted = indexBoundary + 1; indexUnsorted < size; ++indexUnsorted)
                if (array[indexUnsorted] < array[minValueUnsorted]) {
                    minValueUnsorted = indexUnsorted;
                }

            int temp = array[minValueUnsorted];
            array[minValueUnsorted] = array[indexBoundary];
            array[indexBoundary] = temp;
        }
    }
}
