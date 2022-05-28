package com.company.server.algorithms;

public class AlgorithmTimer {

    public static long getExecutionTime(Runnable function) {

        long startTime = System.nanoTime();
        function.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
