package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InputsFSDValidator implements FunctionalStageDefinitionValidator {
    private final StageDefinitionStore stageDefinitionStore;
    private final StageStore stageStore;

    public InputsFSDValidator(StageDefinitionStore stageDefinitionStore, StageStore stageStore) {
        this.stageDefinitionStore = stageDefinitionStore;
        this.stageStore = stageStore;
    }

    @Override
    public boolean isValid(FunctionalStageDefinition definition) {
        Set<FsStage> functionalSubStages = definition.getFunctionalSubStages();
        Map<String, List<StageDefinition>> rootsDefinitionByInputs = getRootsInputDefinitions(functionalSubStages);
        Map<String, StageInputDefinition> functionalStageInputDefinitions = definition.getInputsByName();

        if (rootsDefinitionByInputs.size() != functionalStageInputDefinitions.size()) {
            throw new RuntimeException("Expected as much inputs as roots this functional stage declares: " + rootsDefinitionByInputs.size());
        } else {

            rootsDefinitionByInputs.forEach((inputName, rootDefinitions) -> {
                StageInputDefinition stageInputDefinition = functionalStageInputDefinitions.get(inputName);
                if (stageInputDefinition == null) {
                    throw new RuntimeException("Expected input name " + inputName + " in functional Stage's input definitions, but not found.");
                } else if (isInputValid(inputName, stageInputDefinition, rootDefinitions)) {
                    throw new RuntimeException("Different definition for input name " + inputName + " between FunctionalStage and its roots.");
                }
            });
        }
        return true;
    }

    private Map<String, List<StageDefinition>> getRootsInputDefinitions(Set<FsStage> functionalSubStages) {
        return functionalSubStages.stream()
                .filter(this::isRoot)
                .map(fsStage -> this.stageStore.getById(fsStage.getContextualizedId().getStageId()))
                .peek(fsStage -> {
                    if (fsStage == null)
                        throw new RuntimeException("Stage referenced but does not exist.");
                })
                .map(stage -> this.stageDefinitionStore.getById(stage.getDefinitionId()))
                .peek(fsStage -> {
                    if (fsStage == null)
                        throw new RuntimeException("Stage referenced but does not exist.");
                })
                .flatMap(stageDefinition ->
                        stageDefinition.getInputsByName().entrySet().stream()
                                .map(entry -> Map.entry(entry.getKey(), stageDefinition)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private boolean isRoot(FsStage fsStage) {
        return fsStage.getIoDependencies().stream().allMatch(ioDep -> {
            Set<InputProvider> inputProviders = ioDep.getInputProviders();
            return inputProviders == null || inputProviders.isEmpty();
        });
    }

    private boolean isInputValid(String inputName, StageInputDefinition fsInputDefinition, List<StageDefinition> rootDefinitions) {
        if (fsInputDefinition.isRequired()) {
            return rootDefinitions.stream()
                    .allMatch(rootDef -> areDifferent(fsInputDefinition,
                            rootDef.getInputsByName().get(inputName),
                            true));
        } else {
            long nbRootsWithRequiredInput = rootDefinitions.stream()
                    .filter(rootDefinition -> {
                        StageInputDefinition rootInputDefinition = rootDefinition.getInputsByName().get(inputName);
                        return rootInputDefinition.isRequired();
                    }).count();

            if (nbRootsWithRequiredInput != 0) {
                throw new RuntimeException(String.format(
                        "Functional Stage declares input %s as not required, but %s roots declares it as required input.",
                        inputName, nbRootsWithRequiredInput));
            }
            return rootDefinitions.stream()
                    .allMatch(rootDef -> areDifferent(fsInputDefinition,
                            rootDef.getInputsByName().get(inputName),
                            false));
        }
    }

    private boolean areDifferent(StageInputDefinition existingDefinition, StageInputDefinition val, boolean skipIsRequiredCheck) {
        return existingDefinition.canBeMultiple() != val.canBeMultiple() ||
                !IoTypeComparator.areEquals(existingDefinition.getType(), val.getType(), skipIsRequiredCheck);
    }
}
