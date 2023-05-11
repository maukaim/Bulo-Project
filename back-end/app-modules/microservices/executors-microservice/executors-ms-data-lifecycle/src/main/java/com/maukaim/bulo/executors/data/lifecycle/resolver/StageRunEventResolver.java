package com.maukaim.bulo.executors.data.lifecycle.resolver;

import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.io.executors.system.StageRunEvent;

public interface StageRunEventResolver {
    StageRunEvent resolve(StageRunResult stageRunResult);
}
