package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.Map;
import java.util.function.Function;

public interface OrchestrableContextService<CONTEXT extends OrchestrableRunContext<ID>, ID> {
    CONTEXT computeStageRunUpdateUnderLock(ID contextId, Function<CONTEXT, Map<String, StageRun<?>>> contextUpdator);
}
