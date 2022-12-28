package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.common.utils.IoTypeComparator;
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

import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public boolean isValid(FunctionalStageDefinition definition) {
        Set<FsStage> functionalSubStages = definition.getFunctionalSubStages();
        Set<OutputProvider> outputProviders = definition.getOutputProviders(); //Les leaves Id avec les outputs qu'ils donnent
        Map<String, List<StageDefinition>> leavesDefinitionsByOutputId = validateOutputProviders(outputProviders, functionalSubStages);
        Map<String, StageOutputDefinition> actualOutputs = definition.getOutputsByName();

        validateStageOutputs(leavesDefinitionsByOutputId, actualOutputs);
        return true;
    }

    private void validateStageOutputs(Map<String, List<StageDefinition>> leavesDefinitionsByOutputId, Map<String, StageOutputDefinition> actualOutputs) {
        if (actualOutputs.size() != leavesDefinitionsByOutputId.size()) {
            throw new RuntimeException("Actually declared " + actualOutputs.size() + " outputs, but expected:" + leavesDefinitionsByOutputId.size());
        }

        for (String outputId : actualOutputs.keySet()) {
            List<StageDefinition> leavesDefinitions = leavesDefinitionsByOutputId.get(outputId);
            if (leavesDefinitions == null) {
                throw new RuntimeException("Output declared by functional stage but no providers. Output ID is: " + outputId);
            }

            if (isOutputValid(outputId, actualOutputs.get(outputId), leavesDefinitions)) {
                throw new RuntimeException("Declared output named " + outputId + " but does not match expected type regarding the declared providers:" + leavesDefinitions);
            }
        }
    }

    private boolean isOutputValid(String outputId, StageOutputDefinition fsOutputDefinition, List<StageDefinition> leavesDefinitions) {
        if (!fsOutputDefinition.isAlwaysPresent()) {
            return leavesDefinitions.stream()
                    .allMatch(leaveDef -> areDifferent(fsOutputDefinition,
                            leaveDef.getOutputsByName().get(outputId),
                            true));
        } else {
            long nbLeavesWithAlwaysPresentOutput = leavesDefinitions.stream()
                    .filter(leaveDefinition -> leaveDefinition.getOutputsByName().get(outputId).isAlwaysPresent())
                    .count();

            if (nbLeavesWithAlwaysPresentOutput == 0) {
                throw new RuntimeException(String.format(
                        "Functional Stage declares output %s as always present, but no output provider could guaranty it.",
                        outputId));
            }

            return leavesDefinitions.stream()
                    .allMatch(leaveDef -> areDifferent(fsOutputDefinition,
                            leaveDef.getOutputsByName().get(outputId),
                            false));
        }
    }

    private Map<String, List<StageDefinition>> validateOutputProviders(Set<OutputProvider> outputProviders, Set<FsStage> functionalSubStages) {
        Map<ContextStageId, FsStage> fsStageMap = functionalSubStages.stream()
                .collect(Collectors.toMap(
                        fsStage -> fsStage.getContextualizedId(),
                        fsStage -> fsStage
                ));

        return outputProviders.stream()
                .flatMap(outputProvider -> {
                    ContextStageId contextStageId = outputProvider.getContextStageId();
                    FsStage fsStage = fsStageMap.get(contextStageId);
                    if (fsStage == null) {
                        throw new RuntimeException("Following output provider is not an existing substage " + contextStageId);
                    }
                    Stage stage = this.stageStore.getById(contextStageId.getStageId());
                    if (stage == null) {
                        throw new RuntimeException("Stage referenced as outputProvider, but does not exist.");
                    }
                    StageDefinition stageDefinition = this.stageDefinitionStore.getById(stage.getDefinitionId());
                    if (stageDefinition == null) {
                        throw new RuntimeException("Stage declares definition" + stage.getDefinitionId() + ", but it does not exist.");
                    }

                    Set<String> transferredOutputsIds = outputProvider.getOutputIds();
                    return stageDefinition.getOutputsByName().entrySet().stream()
                            .filter(entry -> transferredOutputsIds.contains(entry.getKey()))
                            .map(entry -> Map.entry(entry.getKey(), stageDefinition));
                })
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private boolean areDifferent(StageOutputDefinition newVal, StageOutputDefinition oldVal, boolean skipIsEqualCheck) {
        return newVal.canBeMultiple() != oldVal.canBeMultiple() ||
                !IoTypeComparator.areEquals(newVal.getType(), oldVal.getType(), skipIsEqualCheck);
    }
}