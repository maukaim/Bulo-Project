package com.maukaim.bulo.runs.orchestrators.data.definition;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Objects;
import java.util.Set;

public class FsStage {
    private final ContextStageId contextStageId;
    private final Set<IoDependency> ioDependencies;

    public FsStage(ContextStageId contextStageId, Set<IoDependency> ioDependencies) {
        this.contextStageId = contextStageId;
        this.ioDependencies = ioDependencies;
    }

    public ContextStageId getContextualizedId() {
        return contextStageId;
    }

    public Set<IoDependency> getIoDependencies() {
        return ioDependencies;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FsStage fsStage = (FsStage) o;
        return contextStageId.equals(fsStage.contextStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextStageId);
    }

    @Override
    public String toString() {
        return "FsStage{" +
                "flowStageId=" + contextStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }
}
