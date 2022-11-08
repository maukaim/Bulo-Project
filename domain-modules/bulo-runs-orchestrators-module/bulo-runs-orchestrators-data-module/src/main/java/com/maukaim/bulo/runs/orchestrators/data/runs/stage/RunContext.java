package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.RunContextType;

import java.util.Set;

public abstract class RunContext<ID> {
    protected final RunContextType runContextType;
    protected final ID contextId;
    protected final Set<RunDependency> stageRunDependencies;

    protected RunContext(RunContextType runContextType, ID contextId,
                         Set<RunDependency> stageRunDependencies) {
        this.runContextType = runContextType;
        this.contextId = contextId;
        this.stageRunDependencies = stageRunDependencies;
    }

    public RunContextType getContextType() {
        return runContextType;
    }

    public ID getContextId() {
        return contextId;
    }

    @Override
    public int hashCode() {
        return getContextId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return getContextId().equals(obj);
    }

    public Set<RunDependency> getStageRunDependencies() {
        return stageRunDependencies;
    }

    @Override
    public String toString() {
        return "Context{" +
                "contextType=" + runContextType +
                ", contextId=" + contextId +
                '}';
    }
}
