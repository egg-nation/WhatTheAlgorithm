package com.company.server.repository.repositories.interfaces;

import com.company.server.model.AlgorithmHistory;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

import java.util.List;

public interface AlgorithmHistoryRepository extends Repository {

    List<AlgorithmHistory> getAlgorithmHistoryList();

    XYSeries getSeriesForMethod(String methodName);

    XYDataset getDatasetForAlgorithm(String algorithmName);

    void insert(String algorithmName, String methodName, int inputLength, long executionTime, int userId);
}
