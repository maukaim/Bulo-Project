package com.maukaim.bulo.executors.core;

import com.maukaim.bulo.executors.data.StageRunner;

public interface RunOperatorProvider {
    RunOperator get(StageRunner runner, StageRunConfig stageRunConfig);
}
