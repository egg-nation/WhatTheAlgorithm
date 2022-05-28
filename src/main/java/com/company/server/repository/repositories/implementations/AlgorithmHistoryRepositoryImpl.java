package com.company.server.repository.repositories.implementations;

import com.company.server.model.AlgorithmHistory;
import com.company.server.repository.repositories.interfaces.AlgorithmHistoryRepository;
import com.company.server.repository.session.DatabaseConnection;
import org.jdbi.v3.core.Jdbi;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgorithmHistoryRepositoryImpl implements AlgorithmHistoryRepository {

    private final Jdbi jdbi;
    private List<AlgorithmHistory> algorithmHistoryList;

    public AlgorithmHistoryRepositoryImpl() {

        this.jdbi = DatabaseConnection.getInstance().getConnection();

        update();
    }

    @Override
    public List<AlgorithmHistory> getAlgorithmHistoryList() {

        return algorithmHistoryList;
    }

    @Override
    public void update() {

        algorithmHistoryList = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM algorithm_history")
                .mapToBean(AlgorithmHistory.class)
                .list());
    }

    @Override
    public XYSeries getSeriesForMethod(String methodName) {

        var series = new XYSeries(methodName);

        algorithmHistoryList.stream()
                .filter(entry -> entry.getMethodName().equals(methodName.trim()))
                .forEach(entry -> series.add(entry.getInputLength(), (double)entry.getExecutionTime()));

        return series;
    }

    @Override
    public XYDataset getDatasetForAlgorithm(String algorithmName) {

        var dataset = new XYSeriesCollection();
        Set<String> methods = new HashSet<>();

        algorithmHistoryList.stream()
                .filter(entry -> entry.getAlgorithmName().equals(algorithmName.trim()))
                .forEach(entry -> methods.add(entry.getMethodName()));

        methods.forEach(method -> dataset.addSeries(getSeriesForMethod(method)));

        return dataset;
    }

    @Override
    public void insert(String algorithmName, String methodName, int inputLength, long executionTime, int userId) {

        jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO algorithm_history (algorithm_name, method_name, input_length, execution_time, user_id) VALUES (:algorithm_name, :method_name, :input_length, :execution_time, :user_id)")
                .bind("algorithm_name", algorithmName)
                .bind("method_name", methodName)
                .bind("input_length", inputLength)
                .bind("execution_time", executionTime)
                .bind("user_id", userId)
                .execute()
        );

        update();
    }

    @Override
    public void delete(int userId) {

        jdbi.withHandle(handle -> handle.createUpdate("DELETE FROM algorithm_history WHERE (user_id = :user_id)")
                .bind("user_id", userId)
                .execute()
        );

        update();
    }
}
