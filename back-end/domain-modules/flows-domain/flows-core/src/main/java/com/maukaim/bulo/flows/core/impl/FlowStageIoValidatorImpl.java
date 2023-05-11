package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.core.FlowStageIoValidator;
import com.maukaim.bulo.flows.core.FlowValidationException;
import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.stage.Stage;

import java.util.*;

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
                    checkSingleOutputProvided(outputId, inputProviderDefinition, stageInputDefinition, false);
                } else {
                    throw new FlowValidationException("Too much outputIds. Expected 1, got " + inputProvider.getOutputIds().size());
                }
            } else {
                throw new FlowValidationException(String.format("Input with name %s expect only one element, but got multiples providers.", inputId));
            }
        } else {
            checkInputProviders(inputProviders, stageInputDefinition, true);
        }
    }

    private void checkInputProviders(Set<InputProvider> inputProviders, StageInputDefinition stageInputDefinition, boolean isMultipleOutputAllowed) throws FlowValidationException {
        if (stageInputDefinition.isRequired()) {
            List<StageOutputDefinition> stageOutputDefinitions = new ArrayList<>();
            for (InputProvider inputProvider : inputProviders) {
                StageDefinition stageDefinition = getStageDefinition(inputProvider);

                Map<String, StageOutputDefinition> outputsByName = stageDefinition.getOutputsByName();
                List<StageOutputDefinition> defs = inputProvider.getOutputIds().stream()
                        .map(outputId -> outputsByName.get(outputId))
                        .toList();
                stageOutputDefinitions.addAll(defs);
            }

            if (!stageOutputDefinitions.stream().anyMatch(StageOutputDefinition::isAlwaysPresent)) {
                throw new RuntimeException("No input provider guaranty there will ALWAYS be a data");
            }else{
                for (StageOutputDefinition stageOutputDefinition : stageOutputDefinitions) {
                    checkOutputDefinitionAgainstInputDefinition(stageOutputDefinition, stageInputDefinition, isMultipleOutputAllowed, true);
                }
            }
        } else {
            for (InputProvider inputProvider : inputProviders) {
                StageDefinition inputProviderDefinition = getStageDefinition(inputProvider);
                for (String outputId : inputProvider.getOutputIds()) {
                    checkOutput(outputId, inputProviderDefinition, stageInputDefinition, true, true);
                }
            }
        }
    }

    private void checkSingleOutputProvided(String outputId, StageDefinition inputProviderDefinition, StageInputDefinition stageInputDefinition, boolean isMultipleOutputAllowed) throws FlowValidationException {
        if (stageInputDefinition.isRequired()) {
            checkOutput(outputId, inputProviderDefinition, stageInputDefinition, isMultipleOutputAllowed, false);
        } else {
            checkOutput(outputId, inputProviderDefinition, stageInputDefinition, isMultipleOutputAllowed, true);
        }
    }

    private void checkOutput(String outputId, StageDefinition inputProviderDefinition, StageInputDefinition stageInputDefinition, boolean isMultipleOutputAllowed, boolean skipIsRequiredCheck) throws FlowValidationException {
        if (inputProviderDefinition != null) {
            Map<String, StageOutputDefinition> outputsByName = inputProviderDefinition.getOutputsByName();
            StageOutputDefinition stageOutputDefinition = outputsByName.get(outputId);
            if (stageOutputDefinition == null) {
                throw new FlowValidationException(String.format("Output %s expected in definition %s. But not found.",
                        outputId, inputProviderDefinition.getStageDefinitionId()));
            }
            checkOutputDefinitionAgainstInputDefinition(stageOutputDefinition, stageInputDefinition, isMultipleOutputAllowed, skipIsRequiredCheck);
        }
    }

    private void checkOutputDefinitionAgainstInputDefinition(StageOutputDefinition stageOutputDefinition, StageInputDefinition stageInputDefinition, boolean multipleOutputAllowed, boolean skipIsRequiredCheck) throws FlowValidationException {
        if (stageOutputDefinition.canBeMultiple() && !multipleOutputAllowed) {
            throw new FlowValidationException("Input Provider potentially provide a collection. Not allowed here.");
        }
        if (!IoTypeComparator.areEquals(stageOutputDefinition.getType(), stageInputDefinition.getType(), skipIsRequiredCheck)) {
            throw new FlowValidationException(String.format("Output of ancestor is type %s but input expected should be type %s",
                    stageOutputDefinition.getType(), stageInputDefinition.getType()));
        }
    }

    private StageDefinition getStageDefinition(InputProvider inputProvider) throws FlowValidationException {
        if (inputProvider.getFlowStageId() == null || inputProvider.getFlowStageId().getStageId() == null) {
            throw new FlowValidationException("Input Provider has no existing flowStageId.");
        }
        String stageId = inputProvider.getFlowStageId().getStageId();
        Stage stage = this.stageService.getById(stageId);
        if (stage == null) {
            throw new FlowValidationException("No Stage for id " + stageId);
        }
        StageDefinition stageDefinition = this.definitionService.getById(stage.getDefinitionId());
        if (stageDefinition == null) {
            throw new FlowValidationException("No Definition for Stage " + stage.getStageId());
        }
        return stageDefinition;
    }
}
