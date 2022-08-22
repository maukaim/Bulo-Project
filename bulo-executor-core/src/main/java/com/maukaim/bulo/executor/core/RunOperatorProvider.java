package com.maukaim.bulo.executor.core;

import com.maukaim.bulo.executor.core.api.StageRunner;

public interface RunOperatorProvider {
    RunOperator get(StageRunner runner, StageRunConfig stageRunConfig);
}
