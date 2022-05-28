package com.company.server.algorithms.categories.array.searching;

import com.company.server.algorithms.StartAlgorithms;
import com.company.server.algorithms.categories.array.searching.methods.*;
import com.company.server.ioc.IoCModuleService;
import com.company.server.model.User;
import com.company.server.service.interfaces.AlgorithmHistoryService;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.company.server.algorithms.AlgorithmTimer.*;

public class StartSearching implements StartAlgorithms {

    private static final String ALGORITHM_NAME = "Searching";

    private final List<Integer> array;
    private final int elementToBeFound;
    private final AlgorithmHistoryService algorithmHistoryService;
    private final User user;

    public StartSearching(List<Integer> array, int elementToBeFound, User user) {

        this.array = new ArrayList<>(array);
        this.elementToBeFound = elementToBeFound;
        this.user = user;

        Injector injector = Guice.createInjector(new IoCModuleService());
        this.algorithmHistoryService = injector.getInstance(AlgorithmHistoryService.class);
    }

    @Override
    public void start() {

        List<Integer> sortedArray = new ArrayList<>(List.copyOf(array));
        sortedArray.sort(Comparator.naturalOrder());

        AtomicReference<SearchMethod> linearSearch = new AtomicReference<>(new LinearSearch(array, elementToBeFound));

        algorithmHistoryService.addEntry(
                ALGORITHM_NAME,
                linearSearch.get().getMethodName(),
                array.size(),
                getExecutionTime(() -> linearSearch.get().startAlgorithm()),
                user.getId()
        );

        if (sortedArray.equals(array)) {

            List<AtomicReference<SearchMethod>> searchingAlgorithms = Arrays.asList(
                    new AtomicReference<>(new BinarySearch(array, elementToBeFound)),
                    new AtomicReference<>(new InterpolationSearch(array, elementToBeFound)),
                    new AtomicReference<>(new TernarySearch(array, elementToBeFound))
            );

            searchingAlgorithms.forEach(algorithm ->
                algorithmHistoryService.addEntry(
                        ALGORITHM_NAME,
                        algorithm.get().getMethodName(),
                        array.size(),
                        getExecutionTime(() -> algorithm.get().startAlgorithm()),
                        user.getId()
                )
            );
        }
    }
}
