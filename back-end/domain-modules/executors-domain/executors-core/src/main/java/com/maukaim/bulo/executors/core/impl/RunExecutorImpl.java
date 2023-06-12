package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.RunExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class RunExecutorImpl implements RunExecutor {
    private final ExecutorService executorService;

    public RunExecutorImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<?> async(Runnable callable) {
        return this.executorService.submit(callable);
    }
}
