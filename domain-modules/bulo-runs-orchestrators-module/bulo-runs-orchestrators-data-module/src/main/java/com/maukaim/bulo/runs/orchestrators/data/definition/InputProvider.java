package com.maukaim.bulo.runs.orchestrators.data.definition;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Set;

public class InputProvider {
    private final ContextualizedStageId contextualizedStageId;
    private final Set<String> outputIds;

    public InputProvider(ContextualizedStageId contextualizedStageId, Set<String> outputIds) {
        this.contextualizedStageId = contextualizedStageId;
        this.outputIds = outputIds;
    }

    public ContextualizedStageId getFlowStageId() {
        return contextualizedStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public String toString() {
        return "InputProvider{" +
                "flowStageId=" + contextualizedStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
