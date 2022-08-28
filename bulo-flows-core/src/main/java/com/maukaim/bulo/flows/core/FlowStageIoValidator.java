package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;

import java.util.Map;
import java.util.Set;

public interface FlowStageIoValidator {
    void validate(String inputId, StageInputDefinition stageInputDefinition, Map<StageDefinition, Set<String>> inputProviderDefinitions) throws FlowValidationException;
}
