package com.company.server.algorithms.categories.array.sorting.methods;

public class CocktailSort implements SortMethod {

    private static final String METHOD_NAME = "Cocktail Sort";

    private final int[] array;
    private final int size;

    public CocktailSort(int[] array) {

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

        boolean swapped = true;
        int start = 0;
        int end = size;

        while (swapped) {
            swapped = false;
            for (int index = start; index < end - 1; ++index) {
                if (array[index] > array[index + 1]) {
                    int temp = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }

            swapped = false;
            --end;

            for (int index = end - 1; index >= start; --index) {
                if (array[index] > array[index + 1]) {
                    int temp = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = temp;
                    swapped = true;
                }
            }

            ++start;
        }
    }
}
