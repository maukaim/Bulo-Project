package com.maukaim.bulo.commons.io.instructions.models.functional;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Set;

public class FsStageDto {
    private final ContextualizedStageId contextualizedStageId;
    private final Set<IoDependencyDto> ioDependencies;

    public FsStageDto(ContextualizedStageId contextualizedStageId, Set<IoDependencyDto> ioDependencies) {
        this.contextualizedStageId = contextualizedStageId;
        this.ioDependencies = ioDependencies;
    }

    public ContextualizedStageId getFsStageId() {
        return contextualizedStageId;
    }

    public Set<IoDependencyDto> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FsStage{" +
                "flowStageId=" + contextualizedStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }
}
