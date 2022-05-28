package com.company.server.algorithms.categories.array.sorting.methods;

public class InsertionSort implements SortMethod {

    private static final String METHOD_NAME = "Insertion Sort";

    private final int[] array;
    private final int size;

    public InsertionSort(int[] array) {

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

        algorithm(size - 1);

        int lastElement = array[size - 1];
        int position = size - 2;

        while (position >= 0 && array[position] > lastElement) {
            array[position + 1] = array[position];
            --position;
        }

        array[position + 1] = lastElement;
    }
}
