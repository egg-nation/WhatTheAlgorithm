package com.company.server.algorithms.categories.array.sorting.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    void getResult() {

        // given
        int[] array = {12, -10, 54, 15, 67, -90, 43, 0};
        int[] expectedArray = {-90, -10, 0, 12, 15, 43, 54, 67};
        HeapSort heapSort = new HeapSort(array);
        heapSort.startAlgorithm();

        // when
        int[] resultArray = heapSort.getResult();

        // then
        assertArrayEquals(resultArray, expectedArray);
    }
}