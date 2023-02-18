package com.maukaim.bulo.io.runs.orchestrators.system.models.definition;

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
        return "InputProviderDto{" +
                "contextualizedStageId=" + contextStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
