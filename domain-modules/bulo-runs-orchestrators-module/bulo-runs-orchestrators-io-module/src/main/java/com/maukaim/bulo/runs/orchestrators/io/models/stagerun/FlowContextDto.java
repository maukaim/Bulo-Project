package com.maukaim.bulo.runs.orchestrators.io.models.stagerun;


public class FlowContextDto extends ContextDto<String> {
    public FlowContextDto(String contextId) {
        super(RunContextTypeDto.FUNCTIONAL_STAGE_RUN, contextId);
    }
}
