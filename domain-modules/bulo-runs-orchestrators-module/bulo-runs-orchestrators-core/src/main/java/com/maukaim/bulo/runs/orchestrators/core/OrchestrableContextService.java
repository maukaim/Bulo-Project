package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.Map;
import java.util.function.Function;

public interface OrchestrableContextService<CONTEXT extends OrchestrableContext<ID>, ID> {
    CONTEXT computeStageRunUpdateUnderLock(ID contextId, Function<CONTEXT, Map<String, StageRun>> contextUpdator);
}
