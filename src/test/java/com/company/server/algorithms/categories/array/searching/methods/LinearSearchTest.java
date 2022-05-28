package com.company.server.algorithms.categories.array.searching.methods;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinearSearchTest {

    @Test
    void getResultWhenElementIsFound() {

        // given
        List<Integer> array = Arrays.asList(12, 23, 34, 51, 65, 67, 78, 98);
        int elementToBeFound = 23;
        LinearSearch linearSearch = new LinearSearch(array, elementToBeFound);
        linearSearch.startAlgorithm();

        // when
        int result = linearSearch.getResult();

        // then
        assertEquals(result, 1);
    }

    @Test
    void getResultWhenElementIsNotFound() {

        // given
        List<Integer> array = Arrays.asList(12, 23, 34, 51, 65, 67, 78, 98);
        int elementToBeFound = 102;
        LinearSearch linearSearch = new LinearSearch(array, elementToBeFound);
        linearSearch.startAlgorithm();

        // when
        int result = linearSearch.getResult();

        // then
        assertEquals(result, -1);
    }
}