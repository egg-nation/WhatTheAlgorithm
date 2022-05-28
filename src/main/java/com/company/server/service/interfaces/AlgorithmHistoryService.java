package com.company.server.service.interfaces;

import com.company.server.model.AlgorithmHistory;

import java.util.List;

public interface AlgorithmHistoryService extends Service {

    void addEntry(String algorithmName, String methodName, int inputLength, long executionTime, int userId);

    void viewChartForAlgorithm(String algorithmName);

    List<AlgorithmHistory> getHistory();

    void deleteHistory(int userId);
}
