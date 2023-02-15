package com.maukaim.bulo.runs.orchestrators.io.models.stagerun;

import java.util.Set;

public class FunctionalStageRunContextDto extends RunContextDto<String> {
    public FunctionalStageRunContextDto(String contextId, Set<StageRunDependencyDto> stageRunDependencies) {
        super(RunContextTypeDto.FUNCTIONAL_STAGE_RUN, contextId, stageRunDependencies);
    }
}
