package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.RunContextType;

public class FlowContext extends Context<String> {
    public FlowContext(String contextId) {
        super(RunContextType.FUNCTIONAL_STAGE_RUN, contextId);
    }
}
