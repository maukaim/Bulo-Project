package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.RunContextTypeDto;

import java.util.Map;

public interface OrchestrableContextDto<KEY> {

    RunContextTypeDto getContextType();

    KEY getContextId();

    ExecutionGraphDto getExecutionGraph();

    Map<String, StageRunDto> getStageRunByIds();

    OrchestrableContextStatusDto getOrchestrableContextStatus();

}
