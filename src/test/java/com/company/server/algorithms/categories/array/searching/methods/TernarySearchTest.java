package com.company.server.algorithms.categories.array.searching.methods;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TernarySearchTest {

    @Test
    void getResultWhenElementIsFound() {

        // given
        List<Integer> array = Arrays.asList(12, 23, 34, 51, 65, 67, 78, 98);
        int elementToBeFound = 23;
        TernarySearch ternarySearch = new TernarySearch(array, elementToBeFound);
        ternarySearch.startAlgorithm();

        // when
        int result = ternarySearch.getResult();

        // then
        assertEquals(result, 1);
    }

    @Test
    void getResultWhenElementIsNotFound() {

        // given
        List<Integer> array = Arrays.asList(12, 23, 34, 51, 65, 67, 78, 98);
        int elementToBeFound = 102;
        TernarySearch ternarySearch = new TernarySearch(array, elementToBeFound);
        ternarySearch.startAlgorithm();

        // when
        int result = ternarySearch.getResult();

        // then
        assertEquals(result, -1);
    }
}