package com.company.server.algorithms.categories.array.sorting;

import com.company.server.algorithms.StartAlgorithms;
import com.company.server.algorithms.categories.array.sorting.methods.*;
import com.company.server.ioc.IoCModuleService;
import com.company.server.model.User;
import com.company.server.service.interfaces.AlgorithmHistoryService;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.company.server.algorithms.AlgorithmTimer.*;

public class StartSorting implements StartAlgorithms {

    private static final String ALGORITHM_NAME = "Sorting";

    private final int[] array;
    private final AlgorithmHistoryService algorithmHistoryService;
    private final User user;

    public StartSorting(List<Integer> list, User user) {

        this.array = list.stream().mapToInt(i->i).toArray();
        this.user = user;

        Injector injector = Guice.createInjector(new IoCModuleService());
        this.algorithmHistoryService = injector.getInstance(AlgorithmHistoryService.class);
    }

    @Override
    public void start() {

        List<AtomicReference<SortMethod>> sortingAlgorithms = Arrays.asList(
                new AtomicReference<>(new BubbleSort(array)),
                new AtomicReference<>(new CocktailSort(array)),
                new AtomicReference<>(new CountingSort(array)),
                new AtomicReference<>(new HeapSort(array)),
                new AtomicReference<>(new InsertionSort(array)),
                new AtomicReference<>(new PigeonholeSort(array)),
                new AtomicReference<>(new QuickSort(array)),
                new AtomicReference<>(new SelectionSort(array))
        );

        sortingAlgorithms.forEach(algorithm ->
            algorithmHistoryService.addEntry(
                    ALGORITHM_NAME,
                    algorithm.get().getMethodName(),
                    array.length,
                    getExecutionTime(() -> algorithm.get().startAlgorithm()),
                    user.getId()
            )
        );
    }
}
