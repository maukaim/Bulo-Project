package com.maukaim.bulo.commons.io.instructions.models.functional;

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
        return "InputProvider{" +
                "flowStageId=" + contextualizedStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
