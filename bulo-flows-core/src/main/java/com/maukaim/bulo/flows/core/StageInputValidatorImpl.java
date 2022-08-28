package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.core.util.FlowUtils;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StageInputValidatorImpl implements StageInputValidator {
    @Override
    public void validate(Set<IoDependency> ioDependencies, StageDefinition definition) throws FlowValidationException {
        Set<String> inputIds = FlowUtils.getInputIds(ioDependencies);

        Map<String, StageInputDefinition> inputsByName = definition.getInputsByName();
        for (Map.Entry<String, StageInputDefinition> inputEntry : inputsByName.entrySet()) {
            if (!inputIds.contains(inputEntry.getKey()) && inputEntry.getValue().isRequired()) {
                throw new FlowValidationException(String.format("Input %s not provided but required by definition %s. Invalid flow.",
                        inputEntry.getKey(), definition.getStageDefinitionId()));
            }
        }

        for (String inputId : inputIds) {
            if (!inputsByName.containsKey(inputId)) {
                throw new FlowValidationException(String.format("Input %s provided to stage but not present in stage definition %s. Invalid flow.",
                        inputId, definition.getStageDefinitionId()));
            }
        }
    }

}
