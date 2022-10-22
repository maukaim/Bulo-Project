package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InputsFSDValidator implements FunctionalStageDefinitionValidator {
    private final StageDefinitionStore stageDefinitionStore;
    private final StageStore stageStore;

    public InputsFSDValidator(StageDefinitionStore stageDefinitionStore, StageStore stageStore) {
        this.stageDefinitionStore = stageDefinitionStore;
        this.stageStore = stageStore;
    }

    @Override
    public boolean validate(FunctionalStageDefinition definition) {
        Set<FsStage> functionalSubStages = definition.getFunctionalSubStages();
        Map<String, StageInputDefinition> rootsInputDefinitions = getRootsInputDefinitions(functionalSubStages);
        Map<String, StageInputDefinition> functionalStageInputDefinitions = definition.getInputsByName();

        if (rootsInputDefinitions.size() != functionalStageInputDefinitions.size()) {
            throw new RuntimeException("Expected as much inputs as roots of this functional stage declares: " + rootsInputDefinitions.size());
        } else {
            rootsInputDefinitions.forEach((inputName, inputDefinition) -> {
                StageInputDefinition stageInputDefinition = functionalStageInputDefinitions.get(inputName);
                if (stageInputDefinition == null) {
                    throw new RuntimeException("Expected input name " + inputName + " in functional Stage inputDefinition, but not found.");
                } else if (areDifferent(inputDefinition, stageInputDefinition)) {
                    throw new RuntimeException("Different definition for input name " + inputName + " between FunctionalStage and its roots.");
                }
            });
        }
        return true;
    }

    private Map<String, StageInputDefinition> getRootsInputDefinitions(Set<FsStage> functionalSubStages) {
        List<Map<String, StageInputDefinition>> stageInputDefinitionMaps = functionalSubStages.stream()
                .filter(this::isRoot)
                .map(fsStage -> this.stageStore.getById(fsStage.getContextualizedId().getStageId()))
                .peek(fsStage -> {
                    if (fsStage == null)
                        throw new RuntimeException("Stage referenced but does not exist.");
                })
                .map(stage -> this.stageDefinitionStore.getById(stage.getDefinitionId()))
                .map(stageDefinition -> stageDefinition.getInputsByName())
                .toList();

        Map<String, StageInputDefinition> result = new HashMap<>();
        for (Map<String, StageInputDefinition> defInputDefinitions : stageInputDefinitionMaps) {
            defInputDefinitions.forEach((key, val) -> {
                StageInputDefinition existingDefinition = result.get(key);
                if (existingDefinition != null) {
                    if (areDifferent(existingDefinition, val)) {
                        throw new RuntimeException("Impossible to create this FunctionalStage. 2 root inputs " + key + " with different definition");
                    }
                } else {
                    result.put(key, val);
                }
            });
        }
        return result;
    }

    private boolean areDifferent(StageInputDefinition existingDefinition, StageInputDefinition val) {
        return existingDefinition.isCanBeMultiple() != val.isCanBeMultiple() ||
                existingDefinition.isRequired() != val.isRequired() ||
                !existingDefinition.getTypeId().equalsIgnoreCase(val.getTypeId());
    }


    private boolean isRoot(FsStage fsStage) {
        return fsStage.getIoDependencies().stream().allMatch(ioDep -> {
            Set<InputProvider> inputProviders = ioDep.getInputProviders();
            return inputProviders == null || inputProviders.isEmpty();
        });
    }
}
