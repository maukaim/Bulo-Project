package com.maukaim.bulo.executor.core;

import java.util.Map;
import java.util.Set;

public interface StageRunEventProcessor {
    void onStageRunRequest(String globalStageId,
                           String stageRunId,
                           Map<String, Map<String, Set<String>>> ancestorsOutputByInputName);
}
