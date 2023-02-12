package com.maukaim.bulo.runs.orchestrators.io.models.stagerun;

import java.util.Set;

public class FlowRunContextDto extends RunContextDto<String> {
    public FlowRunContextDto(String contextId) {
        super(RunContextTypeDto.FUNCTIONAL_STAGE_RUN, contextId, Set.of());
    }
}
