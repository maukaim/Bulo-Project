package com.maukaim.bulo.io.definitions.client.dtos.functional;

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
        return "FsStage{" +
                "flowStageId=" + contextStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }
}
