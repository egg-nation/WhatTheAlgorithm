package com.company.server.algorithms.categories.array.sorting.methods;

public class HeapSort implements SortMethod {

    private static final String METHOD_NAME = "Heap Sort";

    private final int[] array;
    private final int size;

    public HeapSort(int[] array) {

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

        for (int index = size / 2 - 1; index >= 0; --index) {
            heapify(size, index);
        }

        for (int index = size - 1; index > 0; --index) {
            int swap = array[0];
            array[0] = array[index];
            array[index] = swap;

            heapify(index, 0);
        }
    }

    private void heapify(int size, int rootIndex) {

        int largestIndex = rootIndex;
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        if (leftChildIndex < size && array[leftChildIndex] > array[largestIndex]) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < size && array[rightChildIndex] > array[largestIndex]) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != rootIndex) {
            int swap = array[rootIndex];
            array[rootIndex] = array[largestIndex];
            array[largestIndex] = swap;

            heapify(size, largestIndex);
        }
    }
}
