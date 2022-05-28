package com.company.server.algorithms.categories.array.sorting.methods;

import java.util.Arrays;

public class CountingSort implements SortMethod {

    private static final String METHOD_NAME = "Counting Sort";

    private final int[] array;
    private final int size;

    public CountingSort(int[] array) {

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

        int maxValue = Arrays.stream(array).max().orElseThrow();
        int minValue = Arrays.stream(array).min().orElseThrow();

        int range = maxValue - minValue + 1;
        int[] count = new int[range];
        int[] output = new int[size];

        for (int index = 0; index < size; ++index) {
            count[array[index] - minValue]++;
        }

        for (int index = 1; index < count.length; ++index) {
            count[index] += count[index - 1];
        }

        for (int index = size - 1; index >= 0; --index) {
            output[count[array[index] - minValue] - 1] = array[index];
            count[array[index] - minValue]--;
        }

        if (size >= 0) {
            System.arraycopy(output, 0, array, 0, size);
        }
    }
}
