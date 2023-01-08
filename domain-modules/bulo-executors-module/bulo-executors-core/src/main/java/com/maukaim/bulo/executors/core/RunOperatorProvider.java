package com.maukaim.bulo.executors.core;

import com.maukaim.bulo.runners.api.StageRunner;

public interface RunOperatorProvider {
    RunOperator get(StageRunner runner, StageRunConfig stageRunConfig);
}