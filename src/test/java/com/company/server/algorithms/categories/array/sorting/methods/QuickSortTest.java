package com.company.server.algorithms.categories.array.sorting.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void getResult() {

        // given
        int[] array = {12, -10, 54, 15, 67, -90, 43, 0};
        int[] expectedArray = {-90, -10, 0, 12, 15, 43, 54, 67};
        QuickSort quickSort = new QuickSort(array);
        quickSort.startAlgorithm();

        // when
        int[] resultArray = quickSort.getResult();

        // then
        assertArrayEquals(resultArray, expectedArray);
    }
}