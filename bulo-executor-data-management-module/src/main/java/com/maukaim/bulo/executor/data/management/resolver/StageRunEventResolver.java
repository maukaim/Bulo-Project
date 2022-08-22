package com.maukaim.bulo.executor.data.management.resolver;

import com.maukaim.bulo.executor.core.api.result.StageRunResult;
import com.maukaim.bulo.executor.io.out.StageRunEvent;

public interface StageRunEventResolver {
    StageRunEvent resolve(StageRunResult stageRunResult);
}
