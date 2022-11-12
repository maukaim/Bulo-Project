package com.maukaim.bulo.runs.orchestrators.io.models.definition;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public class FsStageDto {
    private final ContextStageId contextStageId;
    private final Set<IoDependencyDto> ioDependencies;

    public FsStageDto(ContextStageId contextStageId, Set<IoDependencyDto> ioDependencies) {
        this.contextStageId = contextStageId;
        this.ioDependencies = ioDependencies;
    }

    public ContextStageId getFsStageId() {
        return contextStageId;
    }

    public Set<IoDependencyDto> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FsStageDto{" +
                "contextStageId=" + contextStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }
}
