package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.flows.core.FlowStageIoValidator;
import com.maukaim.bulo.flows.core.FlowValidationException;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;

import java.util.Map;
import java.util.Set;

public class FlowStageIoValidatorImpl implements FlowStageIoValidator {
    @Override
    public void validate(String inputId, StageInputDefinition stageInputDefinition, Map<StageDefinition, Set<String>> inputProviderDefinitions) throws FlowValidationException {
        if (inputProviderDefinitions.size() == 0 && stageInputDefinition.isRequired()) {
            throw new FlowValidationException("Problem, we expect this input to get at least 1 provider: " + inputId);
        }

        if (!stageInputDefinition.canBeMultiple()) {
            Set<StageDefinition> providers = inputProviderDefinitions.keySet();
            if (providers.size() == 1 ) {
                StageDefinition providerDefinition = providers.iterator().next();
                Set<String> providerOutputNames = providerDefinition.getOutputsByName().keySet();
                if ((providerOutputNames.size() == 1)) {
                    String outputName = providerOutputNames.iterator().next();
                    StageOutputDefinition providerOutputDefinition = providerDefinition.getOutputsByName().get(outputName);
                    if(!providerOutputDefinition.canBeMultiple()) {

                        return;
                    }
                }
            }

            throw new FlowValidationException(String.format("Input with name %s expect only one element, but got multiples from provider(s)", inputId));
        } else {
            for (Map.Entry<StageDefinition, Set<String>> providerEntry : inputProviderDefinitions.entrySet()) {
                Map<String,StageOutputDefinition> outputDefinitionsByName = providerEntry.getKey().getOutputsByName();

                for (String usedOutputName : providerEntry.getValue()) {
                    StageOutputDefinition outputDefinition = outputDefinitionsByName.get(usedOutputName);
                    if(outputDefinition == null){
                        throw new FlowValidationException(String.format(
                                "Provider does not have output named %s to be provided to input %s. We can't validate.",
                                usedOutputName, inputId));
                    }

                    if(!outputDefinition.getIOTypeId().equalsIgnoreCase(stageInputDefinition.getIOTypeId())){
                        throw new FlowValidationException(String.format("For provider definition %s on input %s, type validation failed. Provided %s but this input expect type %s",
                                providerEntry.getKey().getStageDefinitionId(), inputId, outputDefinition.getIOTypeId(), stageInputDefinition.getIOTypeId()));
                    }
                }
            }
        }
    }
}
