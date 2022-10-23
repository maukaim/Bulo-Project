package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.RunContextType;

public abstract class Context<ID> {
    protected final RunContextType runContextType;
    protected final ID contextId;

    protected Context(RunContextType runContextType, ID contextId) {
        this.runContextType = runContextType;
        this.contextId = contextId;
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

    @Override
    public String toString() {
        return "Context{" +
                "contextType=" + runContextType +
                ", contextId=" + contextId +
                '}';
    }
}
