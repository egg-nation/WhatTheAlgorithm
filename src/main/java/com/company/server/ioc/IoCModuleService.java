package com.company.server.ioc;

import com.company.server.service.interfaces.AlgorithmHistoryService;
import com.company.server.service.implementations.AlgorithmHistoryServiceImpl;
import com.company.server.service.interfaces.UserService;
import com.company.server.service.implementations.UserServiceImpl;
import com.google.inject.AbstractModule;

public class IoCModuleService extends AbstractModule {

    @Override
    protected void configure() {

        bind(UserService.class).to(UserServiceImpl.class);
        bind(AlgorithmHistoryService.class).to(AlgorithmHistoryServiceImpl.class);
    }
}
