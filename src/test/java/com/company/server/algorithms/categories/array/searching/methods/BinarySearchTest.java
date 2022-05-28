package com.company.server.algorithms.categories.array.searching.methods;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    void getResultWhenElementIsFound() {

        // given
        List<Integer> array = Arrays.asList(12, 23, 34, 51, 65, 67, 78, 98);
        int elementToBeFound = 23;
        BinarySearch binarySearch = new BinarySearch(array, elementToBeFound);
        binarySearch.startAlgorithm();

        // when
        int result = binarySearch.getResult();

        // then
        assertEquals(result, 1);
    }

    @Test
    void getResultWhenElementIsNotFound() {

        // given
        List<Integer> array = Arrays.asList(12, 23, 34, 51, 65, 67, 78, 98);
        int elementToBeFound = 102;
        BinarySearch binarySearch = new BinarySearch(array, elementToBeFound);
        binarySearch.startAlgorithm();

        // when
        int result = binarySearch.getResult();

        // then
        assertEquals(result, -1);
    }
}