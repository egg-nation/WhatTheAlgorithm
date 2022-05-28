package com.company.server.ioc;

import com.company.server.repository.repositories.interfaces.AlgorithmHistoryRepository;
import com.company.server.repository.repositories.implementations.AlgorithmHistoryRepositoryImpl;
import com.company.server.repository.repositories.interfaces.UserRepository;
import com.company.server.repository.repositories.implementations.UserRepositoryImpl;
import com.google.inject.AbstractModule;

public class IoCModuleRepository extends AbstractModule {

    @Override
    protected void configure() {

        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(AlgorithmHistoryRepository.class).to(AlgorithmHistoryRepositoryImpl.class);
    }
}
