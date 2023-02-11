package com.maukaim.bulo.runs.orchestrators.io.models.stagerun;

import java.util.Set;

public class FunctionalStageContextDto extends ContextDto<String> {
    public FunctionalStageContextDto(String contextId, Set<StageRunDependencyDto> stageRunDependencies) {
        super(RunContextTypeDto.FUNCTIONAL_STAGE_RUN, contextId, stageRunDependencies);
    }
}
