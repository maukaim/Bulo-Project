package com.maukaim.bulo.runs.orchestrators.io.models.stagerun;


public abstract class ContextDto<ID> {
    protected final RunContextTypeDto contextType;
    protected final ID contextId;

    protected ContextDto(RunContextTypeDto contextType, ID contextId) {
        this.contextType = contextType;
        this.contextId = contextId;
    }

    public RunContextTypeDto getContextType() {
        return contextType;
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
                "contextType=" + contextType +
                ", contextId=" + contextId +
                '}';
    }
}
