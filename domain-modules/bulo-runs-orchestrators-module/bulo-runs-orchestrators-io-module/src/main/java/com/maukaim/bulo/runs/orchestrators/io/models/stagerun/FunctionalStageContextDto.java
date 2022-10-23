package com.maukaim.bulo.runs.orchestrators.io.models.stagerun;


public class FunctionalStageContextDto extends ContextDto<String> {
    public FunctionalStageContextDto(String contextId) {
        super(RunContextTypeDto.FUNCTIONAL_STAGE_RUN, contextId);
    }
}
