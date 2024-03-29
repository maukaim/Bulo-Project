package com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun;

import java.util.Set;

public abstract class RunContextDto<ID> {
    protected final RunContextTypeDto contextType;
    protected final ID contextId;
    protected final Set<StageRunDependencyDto> stageRunDependencies;

    protected RunContextDto(RunContextTypeDto contextType, ID contextId, Set<StageRunDependencyDto> stageRunDependencies) {
        this.contextType = contextType;
        this.contextId = contextId;
        this.stageRunDependencies = stageRunDependencies;
    }

    public RunContextTypeDto getContextType() {
        return contextType;
    }

    public ID getContextId() {
        return contextId;
    }

    public Set<StageRunDependencyDto> getStageRunDependencies() {
        return stageRunDependencies;
    }

    @Override
    public int hashCode() {
        return getContextId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return getContextId().equals(obj);
    }

    @Override
    public String toString() {
        return "Context{" +
                "contextType=" + contextType +
                ", contextId=" + contextId +
                '}';
    }
}
