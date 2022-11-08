package com.maukaim.bulo.runs.orchestrators.data.definition;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Objects;
import java.util.Set;

public class FsStage {
    private final ContextualizedStageId contextualizedStageId;
    private final Set<IoDependency> ioDependencies;

    public FsStage(ContextualizedStageId contextualizedStageId, Set<IoDependency> ioDependencies) {
        this.contextualizedStageId = contextualizedStageId;
        this.ioDependencies = ioDependencies;
    }

    public ContextualizedStageId getContextualizedId() {
        return contextualizedStageId;
    }

    public Set<IoDependency> getIoDependencies() {
        return ioDependencies;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FsStage fsStage = (FsStage) o;
        return contextualizedStageId.equals(fsStage.contextualizedStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextualizedStageId);
    }

    @Override
    public String toString() {
        return "FsStage{" +
                "flowStageId=" + contextualizedStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }
}
