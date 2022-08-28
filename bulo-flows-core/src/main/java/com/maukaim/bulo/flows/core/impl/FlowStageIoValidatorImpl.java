package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.core.FlowStageIoValidator;
import com.maukaim.bulo.flows.core.FlowValidationException;
import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import com.maukaim.bulo.flows.data.models.stage.TechnicalStage;

import java.util.Map;
import java.util.Set;

public class FlowStageIoValidatorImpl implements FlowStageIoValidator {
    private final DefinitionService definitionService;
    private final StageService stageService;

    public FlowStageIoValidatorImpl(DefinitionService definitionService, StageService stageService) {
        this.definitionService = definitionService;
        this.stageService = stageService;
    }

    @Override
    public void validate(String inputId, StageInputDefinition stageInputDefinition, Set<InputProvider> inputProviders) throws FlowValidationException {
        if ((inputProviders == null || inputProviders.size() == 0) && stageInputDefinition.isRequired()) {
            throw new FlowValidationException("Problem, we expect this input to get at least 1 provider: " + inputId);
        }
        if (!stageInputDefinition.canBeMultiple()) {
            if (inputProviders.size() == 1) {
                InputProvider inputProvider = inputProviders.iterator().next();
                if (inputProvider.getOutputIds().size() == 1) {
                    String outputId = inputProvider.getOutputIds().iterator().next();
                    StageDefinition inputProviderDefinition = getStageDefinition(inputProvider);
                    checkOutput(outputId, inputProviderDefinition, stageInputDefinition, false);
                } else {
                    throw new FlowValidationException("Too much outputIds. Expected 1, got " + inputProvider.getOutputIds().size());
                }
            }else{
                throw new FlowValidationException(String.format("Input with name %s expect only one element, but got multiples providers.", inputId));
            }
        } else {
            for (InputProvider inputProvider : inputProviders) {
                StageDefinition inputProviderDefinition = getStageDefinition(inputProvider);
                for (String outputId : inputProvider.getOutputIds()) {
                    checkOutput(outputId, inputProviderDefinition, stageInputDefinition, true);
                }
            }
        }
    }

    private void checkOutput(String outputId, StageDefinition inputProviderDefinition, StageInputDefinition stageInputDefinition, boolean isMultipleOutputAllowed) throws FlowValidationException {
        if (inputProviderDefinition != null) {
            Map<String, StageOutputDefinition> outputsByName = inputProviderDefinition.getOutputsByName();
            StageOutputDefinition stageOutputDefinition = outputsByName.get(outputId);
            if (stageOutputDefinition == null) {
                throw new FlowValidationException(String.format("Output %s expected in definition %s. But not found.",
                        outputId, inputProviderDefinition.getStageDefinitionId()));
            }
            checkOutputDefinitionAgainstInputDefinition(stageOutputDefinition, stageInputDefinition, isMultipleOutputAllowed);
        }
    }

    private void checkOutputDefinitionAgainstInputDefinition(StageOutputDefinition stageOutputDefinition, StageInputDefinition stageInputDefinition, boolean multipleOutputAllowed) throws FlowValidationException {
        if (stageOutputDefinition.canBeMultiple() && !multipleOutputAllowed) {
            throw new FlowValidationException("Input Provider potentially provide a collection. Not allowed here.");
        }
        if (!stageOutputDefinition.getIOTypeId().equalsIgnoreCase(stageInputDefinition.getIOTypeId())) {
            throw new FlowValidationException(String.format("Output of ancestor is type %s but input expected should be type %s",
                    stageOutputDefinition.getIOTypeId(), stageInputDefinition.getIOTypeId()));
        }
    }

    private StageDefinition getStageDefinition(InputProvider inputProvider) throws FlowValidationException {
        if (inputProvider.getFlowStageId() == null || inputProvider.getFlowStageId().getGlobalStageId() == null) {
            throw new FlowValidationException("Input Provider has no existing flowStageId.");
        }
        String globalStageId = inputProvider.getFlowStageId().getGlobalStageId();
        Stage stage = this.stageService.getById(globalStageId);
        if (stage instanceof TechnicalStage) {
            StageDefinition stageDefinition = this.definitionService.getById(((TechnicalStage) stage).getDefinitionId());
            if (stage == null) {
                throw new FlowValidationException("No Definition for TechnicalStage " + stage.getStageId());
            }
            return stageDefinition;
        } else {
            return null;
        }
    }


//    public void validate(String inputId, StageInputDefinition stageInputDefinition, Map<StageDefinition, Set<String>> inputProviderDefinitions) throws FlowValidationException {
//        if (inputProviderDefinitions.size() == 0 && stageInputDefinition.isRequired()) {
//            throw new FlowValidationException("Problem, we expect this input to get at least 1 provider: " + inputId);
//        }
//
//        if (!stageInputDefinition.canBeMultiple()) {
//            Set<StageDefinition> providers = inputProviderDefinitions.keySet();
//            if (providers.size() == 1 ) {
//                StageDefinition providerDefinition = providers.iterator().next();
//                Set<String> providerOutputNames = providerDefinition.getOutputsByName().keySet();
//                if ((providerOutputNames.size() == 1)) {
//                    String outputName = providerOutputNames.iterator().next();
//                    StageOutputDefinition providerOutputDefinition = providerDefinition.getOutputsByName().get(outputName);
//                    if(!providerOutputDefinition.canBeMultiple()) {
//                        return;
//                    }
//                }
//            }
//
//            throw new FlowValidationException(String.format("Input with name %s expect only one element, but got multiples from provider(s)", inputId));
//        } else {
//            for (Map.Entry<StageDefinition, Set<String>> providerEntry : inputProviderDefinitions.entrySet()) {
//                Map<String,StageOutputDefinition> outputDefinitionsByName = providerEntry.getKey().getOutputsByName();
//
//                for (String usedOutputName : providerEntry.getValue()) {
//                    StageOutputDefinition outputDefinition = outputDefinitionsByName.get(usedOutputName);
//                    if(outputDefinition == null){
//                        throw new FlowValidationException(String.format(
//                                "Provider does not have output named %s to be provided to input %s. We can't validate.",
//                                usedOutputName, inputId));
//                    }
//
//                    if(!outputDefinition.getIOTypeId().equalsIgnoreCase(stageInputDefinition.getIOTypeId())){
//                        throw new FlowValidationException(String.format("For provider definition %s on input %s, type validation failed. Provided %s but this input expect type %s",
//                                providerEntry.getKey().getStageDefinitionId(), inputId, outputDefinition.getIOTypeId(), stageInputDefinition.getIOTypeId()));
//                    }
//                }
//            }
//        }
//    }
}
