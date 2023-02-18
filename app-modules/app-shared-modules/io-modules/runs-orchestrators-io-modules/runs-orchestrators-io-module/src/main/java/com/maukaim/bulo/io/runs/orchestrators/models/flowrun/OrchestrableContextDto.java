package com.maukaim.bulo.io.runs.orchestrators.models.flowrun;

import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.RunContextTypeDto;
import com.maukaim.bulo.io.runs.orchestrators.models.StageRunDto;

import java.util.Map;

public interface OrchestrableContextDto<KEY> {

    RunContextTypeDto getContextType();

    KEY getContextId();

    ExecutionGraphDto getExecutionGraph();

    Map<String, StageRunDto<?>> getStageRunByIds();

    OrchestrableContextStatusDto getOrchestrableContextStatus();

}
