package com.company.server.algorithms.categories.array.sorting.methods;

public class QuickSort implements SortMethod {

    private static final String METHOD_NAME = "Quick Sort";

    private final int[] array;
    private final int size;

    public QuickSort(int[] array) {

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

        algorithm(0, size - 1);
    }

    private void algorithm(int leftIndex, int rightIndex) {

        if (leftIndex < rightIndex) {
            int partitionIndex = partition(leftIndex, rightIndex);

            algorithm(leftIndex, partitionIndex - 1);
            algorithm(partitionIndex + 1, rightIndex);
        }
    }

    private int partition(int leftIndex, int rightIndex) {

        int pivot = array[rightIndex];
        int smallIndex = leftIndex - 1;

        for (int currentIndex = leftIndex; currentIndex <= rightIndex - 1; ++currentIndex) {
            if (array[currentIndex] < pivot) {
                ++smallIndex;
                swap(smallIndex, currentIndex);
            }
        }

        swap(smallIndex + 1, rightIndex);
        return smallIndex + 1;
    }

    private void swap(int index1, int index2) {

        int swap = array[index1];
        array[index1] = array[index2];
        array[index2] = swap;
    }
}
