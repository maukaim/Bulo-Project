package com.maukaim.bulo.runs.orchestrators.io.models.definition;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Set;

public class InputProviderDto {
    private final ContextualizedStageId contextualizedStageId;
    private final Set<String> outputIds;

    public InputProviderDto(ContextualizedStageId contextualizedStageId, Set<String> outputIds) {
        this.contextualizedStageId = contextualizedStageId;
        this.outputIds = outputIds;
    }

    public ContextualizedStageId getFsStageId() {
        return contextualizedStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public String toString() {
        return "InputProviderDto{" +
                "contextualizedStageId=" + contextualizedStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
