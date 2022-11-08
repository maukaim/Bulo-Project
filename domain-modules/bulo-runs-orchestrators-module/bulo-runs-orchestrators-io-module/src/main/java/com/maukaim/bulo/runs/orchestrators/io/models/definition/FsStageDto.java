package com.maukaim.bulo.runs.orchestrators.io.models.definition;

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
        return "FsStageDto{" +
                "contextualizedStageId=" + contextualizedStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }
}
