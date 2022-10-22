package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
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
        List<FsStage> leaves = determineLeaves(functionalSubStages);
        Map<String, StageOutputDefinition> outputsByName = definition.getOutputsByName();
        validateFunctionalStageOutputs(leaves, outputsByName);
        return true;
    }

    private void validateFunctionalStageOutputs(List<FsStage> leaves, Map<String, StageOutputDefinition> fsOutputsByName) {
        Map<String, StageOutputDefinition> possibleOutputs = computePossibleOutputs(leaves);
        fsOutputsByName.forEach((key,val)->{
            StageOutputDefinition stageOutputDefinition = possibleOutputs.get(key);
            if(stageOutputDefinition == null){
                throw new RuntimeException("No sub stage output declare an output named " + key);
            }else if(areDifferent(stageOutputDefinition, val)){
                throw new RuntimeException("Sub stages provide output named "+ key + " but designed it differently:" + stageOutputDefinition);
            }
        });
    }

    private Map<String, StageOutputDefinition> computePossibleOutputs(List<FsStage> leaves) {
        Map<String, StageOutputDefinition> result = new HashMap<>();
        leaves.stream()
                .map(leaf -> this.stageStore.getById(leaf.getContextualizedId().getStageId()))
                .peek(subStage -> {
                    if (subStage == null) {
                        throw new RuntimeException("Impossible to validate Functional Stage, one stage leaf does not exist.");
                    }
                })
                .map(subStage -> this.stageDefinitionStore.getById(subStage.getDefinitionId()))
                .map(subStageDefinition -> subStageDefinition.getOutputsByName())
                .flatMap(outputsByName -> outputsByName.entrySet().stream())
                .forEach(outputEntry -> result.compute(outputEntry.getKey(), (key, val) -> {
                            if (val == null) {
                                return outputEntry.getValue();
                            } else if (areDifferent(outputEntry.getValue(), val)) {
                                throw new RuntimeException("Conflict in Outputs. Same name, but not same value: " + key);
                            } else if (!val.isCanBeMultiple()) {
                                return new StageOutputDefinition(true, val.getTypeId());
                            }
                            return val;
                        }));
        return result;
    }

    private boolean areDifferent(StageOutputDefinition newVal, StageOutputDefinition oldVal) {
        return newVal.isCanBeMultiple() != oldVal.isCanBeMultiple() ||
                !newVal.getTypeId().equalsIgnoreCase(oldVal.getTypeId());
    }

    private List<FsStage> determineLeaves(Set<FsStage> functionalSubStages) {
        List<FsStage> leaves = new ArrayList<>(functionalSubStages);
        Set<ContextualizedStageId> notLeaveIds = new HashSet<>();

        for (FsStage functionalSubStage : functionalSubStages) {
            functionalSubStage.getIoDependencies().stream()
                    .map(ioDependency -> ioDependency.getInputProviders())
                    .filter(Objects::nonNull)
                    .flatMap(Collection::stream)
                    .map(inputProvider -> inputProvider.getFlowStageId())
                    .forEach(notLeaveIds::add);
        }

        return leaves.stream()
                .filter(fsStage -> !notLeaveIds.contains(fsStage.getContextualizedId()))
                .collect(Collectors.toList());
    }
}
