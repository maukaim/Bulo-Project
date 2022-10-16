package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;

import java.util.Set;

public interface FlowStageIoValidator {
    void validate(String inputId, StageInputDefinition stageInputDefinition, Set<InputProvider> inputProviders) throws FlowValidationException;
}
