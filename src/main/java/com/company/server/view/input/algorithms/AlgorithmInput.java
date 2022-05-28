package com.company.server.view.input.algorithms;

import java.io.IOException;
import java.sql.SQLException;

@FunctionalInterface
public interface AlgorithmInput {

    void get() throws SQLException, IOException;
}
