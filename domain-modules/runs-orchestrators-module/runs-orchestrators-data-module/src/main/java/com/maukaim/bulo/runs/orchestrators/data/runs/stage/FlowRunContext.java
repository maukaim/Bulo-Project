package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.RunContextType;

import java.util.Set;

public class FlowRunContext extends RunContext<String> {
    public FlowRunContext(String contextId) {
        super(RunContextType.FLOW_RUN, contextId, Set.of());
    }
}
