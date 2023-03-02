package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.RunContextType;

import java.util.Set;

public class FunctionalStageRunContext extends RunContext<String> {
    public FunctionalStageRunContext(String contextId, Set<RunDependency> stageRunDependencies) {
        super(RunContextType.FUNCTIONAL_STAGE_RUN, contextId, stageRunDependencies);
    }
}
