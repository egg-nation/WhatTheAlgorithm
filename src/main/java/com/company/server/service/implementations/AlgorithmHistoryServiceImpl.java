package com.company.server.service.implementations;

import com.company.server.ioc.IoCModuleRepository;
import com.company.server.model.AlgorithmHistory;
import com.company.server.repository.repositories.interfaces.AlgorithmHistoryRepository;
import com.company.server.service.interfaces.AlgorithmHistoryService;
import com.company.server.vizualization.Chart;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.awt.*;
import java.util.List;

public class AlgorithmHistoryServiceImpl implements AlgorithmHistoryService {

    public AlgorithmHistoryRepository algorithmHistoryRepository;

    public AlgorithmHistoryServiceImpl()  {

        Injector injector = Guice.createInjector(new IoCModuleRepository());
        this.algorithmHistoryRepository = injector.getInstance(AlgorithmHistoryRepository.class);
    }

    @Override
    public void addEntry(String algorithmName, String methodName, int inputLength, long executionTime, int userId) {

        algorithmHistoryRepository.insert(algorithmName, methodName, inputLength, executionTime, userId);
    }

    @Override
    public void viewChartForAlgorithm(String algorithmName) {

        EventQueue.invokeLater(() -> {

            Chart chart = new Chart(algorithmName, algorithmHistoryRepository.getDatasetForAlgorithm(algorithmName));
            chart.setVisible(true);
        });
    }

    @Override
    public List<AlgorithmHistory> getHistory() {

        return algorithmHistoryRepository.getAlgorithmHistoryList();
    }

    @Override
    public void deleteHistory(int userId) {

        algorithmHistoryRepository.delete(userId);
    }
}
