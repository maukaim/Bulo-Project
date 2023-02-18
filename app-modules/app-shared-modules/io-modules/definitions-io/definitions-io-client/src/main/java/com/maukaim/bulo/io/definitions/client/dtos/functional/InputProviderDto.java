package com.maukaim.bulo.io.definitions.client.dtos.functional;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public class InputProviderDto {
    private final ContextStageId contextStageId;
    private final Set<String> outputIds;

    public InputProviderDto(ContextStageId contextStageId, Set<String> outputIds) {
        this.contextStageId = contextStageId;
        this.outputIds = outputIds;
    }

    public ContextStageId getFsStageId() {
        return contextStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public String toString() {
        return "InputProvider{" +
                "flowStageId=" + contextStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
