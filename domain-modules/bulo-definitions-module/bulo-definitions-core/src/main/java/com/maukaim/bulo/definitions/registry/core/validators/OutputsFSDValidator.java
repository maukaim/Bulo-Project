package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;
import com.maukaim.bulo.definitions.data.stage.Stage;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;

import java.util.*;
import java.util.stream.Collectors;

public class OutputsFSDValidator implements FunctionalStageDefinitionValidator {
    private final StageStore stageStore;
    private final StageDefinitionStore stageDefinitionStore;

    public OutputsFSDValidator(StageStore stageStore,
                               StageDefinitionStore stageDefinitionStore) {
        this.stageStore = stageStore;
        this.stageDefinitionStore = stageDefinitionStore;
    }


    @Override
    public boolean validate(FunctionalStageDefinition definition) {
        Set<FsStage> functionalSubStages = definition.getFunctionalSubStages();
        Set<OutputProvider> outputProviders = definition.getOutputProviders();

        Map<String, StageOutputDefinition> expectedStageOutputs = validateOutputProviders(outputProviders, functionalSubStages);
        Map<String, StageOutputDefinition> actualOutputs = definition.getOutputsByName();
        validateStageOutputs(expectedStageOutputs, actualOutputs);
        return true;
    }

    private void validateStageOutputs(Map<String, StageOutputDefinition> expected, Map<String, StageOutputDefinition> actual) {
        if (actual.size() != expected.size()) {
            throw new RuntimeException("Actually declared " + actual.size() + " outputs, but expected:" + expected.size());
        }

        for (String outputId : actual.keySet()) {
            StageOutputDefinition expectedStageOutputDefinition = expected.get(outputId);
            if (expectedStageOutputDefinition == null) {
                throw new RuntimeException("Output declared but not expected: " + outputId);
            }
            if (areDifferent(expectedStageOutputDefinition, actual.get(outputId))) {
                throw new RuntimeException("Declared output named " + outputId + " but designed it differently than the expected:" + expectedStageOutputDefinition);
            }
        }
    }

    private Map<String, StageOutputDefinition> validateOutputProviders(Set<OutputProvider> outputProviders, Set<FsStage> functionalSubStages) {
        Map<ContextStageId, FsStage> fsStageMap = functionalSubStages.stream()
                .collect(Collectors.toMap(
                        fsStage -> fsStage.getContextualizedId(),
                        fsStage -> fsStage
                ));
        Map<String, StageOutputDefinition> result = new HashMap<>();
        for (OutputProvider outputProvider : outputProviders) {
            ContextStageId contextStageId = outputProvider.getContextStageId();
            FsStage fsStage = fsStageMap.get(contextStageId);
            if (fsStage == null) {
                throw new RuntimeException("Following output provider is not an existing substage " + contextStageId);
            }
            Stage stage = this.stageStore.getById(contextStageId.getStageId());
            if (stage == null) {
                throw new RuntimeException("No existing stage found for id " + contextStageId.getStageId());
            }

            StageDefinition definition = this.stageDefinitionStore.getById(stage.getDefinitionId());
            if (definition == null) {
                throw new RuntimeException(String.format("For stage %s, did not found definition %s",
                        stage.getStageId(),
                        stage.getDefinitionId())
                );
            }
            Map<String, StageOutputDefinition> actualOutputsMap = definition.getOutputsByName();
            Map<String, StageOutputDefinition> subOutputResult = new HashMap<>();
            for (String outputId : outputProvider.getOutputIds()) {
                StageOutputDefinition stageOutputDefinition = actualOutputsMap.get(outputId);
                if (stageOutputDefinition == null) {
                    throw new RuntimeException(String.format("Output %s not found as an output of definition %s. Existing outputs are: %s ",
                            outputId,
                            definition.getDefinitionId(),
                            actualOutputsMap));
                }
                subOutputResult.put(outputId, stageOutputDefinition);
            }

            for (String outputId : subOutputResult.keySet()) {
                StageOutputDefinition alreadyExistingOutputDefinition = result.get(outputId);
                if (alreadyExistingOutputDefinition == null) {
                    result.put(outputId, subOutputResult.get(outputId));
                } else if (!hasSameType(alreadyExistingOutputDefinition, subOutputResult.get(outputId))) {
                    throw new RuntimeException(String.format("Same outputId but not same object type. %s and %s.",
                            alreadyExistingOutputDefinition));
                } else {
                    result.put(outputId, new StageOutputDefinition(true, alreadyExistingOutputDefinition.getTypeId()));
                }
            }
        }
        return result;
    }

    private boolean areDifferent(StageOutputDefinition newVal, StageOutputDefinition oldVal) {
        return newVal.isCanBeMultiple() != oldVal.isCanBeMultiple() ||
                !hasSameType(newVal, oldVal);
    }

    private boolean hasSameType(StageOutputDefinition first, StageOutputDefinition second) {
        return first.getTypeId().equalsIgnoreCase(second.getTypeId());
    }
}

/**
 * Now need 1) to propagate the OutputProviders to dto
 * 2) test creation in def service
 * 3) to propagate to Orchestrator service dto
 * 4) to propagate to Orchestrator service model
 * 5) use it in OrchestrableUtils to provide a FSRun dependent with real run and real results.
 */
