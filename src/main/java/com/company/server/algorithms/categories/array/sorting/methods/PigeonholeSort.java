package com.company.server.algorithms.categories.array.sorting.methods;

import java.util.Arrays;

public class PigeonholeSort implements SortMethod {

    private static final String METHOD_NAME = "Pigeonhole Sort";

    private final int[] array;
    private final int size;

    public PigeonholeSort(int[] array) {

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
        int[] phole = new int[range];
        Arrays.fill(phole, 0);

        for(int index = 0; index < size; ++index) {
            phole[array[index] - minValue]++;
        }

        int indexArray = 0;
        for (int indexRange = 0; indexRange < range; ++indexRange) {
            while(phole[indexRange]-->0) {
                array[indexArray++] = indexRange + minValue;
            }
        }
    }
}
