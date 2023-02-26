package com.maukaim.bulo.definitions.data.definition.functional;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public class OutputProvider {
    private final ContextStageId contextStageId;
    private final Set<String> outputIds;

    public OutputProvider(ContextStageId contextStageId, Set<String> outputIds) {
        this.contextStageId = contextStageId;
        this.outputIds = outputIds;
    }

    public ContextStageId getContextStageId() {
        return contextStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public String toString() {
        return "OutputProvider{" +
                "contextStageId=" + contextStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}

