package com.company.server.algorithms.categories.array.sorting.methods;

public class BubbleSort implements SortMethod {

    private static final String METHOD_NAME = "Bubble Sort";

    private final int[] array;
    private final int size;

    public BubbleSort(int[] array) {

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

        algorithm(size);
    }

    private void algorithm(int size) {

        if (size <= 1) {
            return;
        }

        for (int index = 0; index < size - 1; ++index) {
            if (array[index] > array[index + 1]) {
                int swap = array[index];
                array[index] = array[index + 1];
                array[index + 1] = swap;
            }
        }

        algorithm(size - 1);
    }
}
