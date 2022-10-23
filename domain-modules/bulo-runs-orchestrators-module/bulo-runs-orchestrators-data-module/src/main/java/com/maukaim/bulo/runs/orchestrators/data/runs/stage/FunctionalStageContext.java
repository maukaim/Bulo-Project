package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.RunContextType;

public class FunctionalStageContext extends Context<String> {
    public FunctionalStageContext(String contextId) {
        super(RunContextType.FUNCTIONAL_STAGE_RUN, contextId);
    }
}
