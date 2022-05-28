package com.company.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmHistory {

    private int id;
    private String algorithmName;
    private String methodName;
    private int inputLength;
    private long executionTime;
    private int userId;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof AlgorithmHistory)) return false;
        AlgorithmHistory that = (AlgorithmHistory) o;
        return getId() == that.getId() && getInputLength() == that.getInputLength() && getExecutionTime() == that.getExecutionTime() && getUserId() == that.getUserId() && getAlgorithmName().equals(that.getAlgorithmName()) && getMethodName().equals(that.getMethodName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getAlgorithmName(), getMethodName(), getInputLength(), getExecutionTime(), getUserId());
    }

    @Override
    public String toString() {

        return "AlgorithmHistory{" +
                "id=" + id +
                ", algorithmName='" + algorithmName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", inputLength=" + inputLength +
                ", executionTime=" + executionTime +
                ", userId=" + userId +
                '}';
    }
}
