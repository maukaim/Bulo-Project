package com.maukaim.bulo.executor.core;

import com.maukaim.bulo.executor.core.api.TechnicalStageRunner;

import java.util.List;
import java.util.Map;

public interface ExecutorService {
    void executeAsync(Runnable runnable);
}
