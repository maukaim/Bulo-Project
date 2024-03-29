package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class FsStagesFSDValidator implements FunctionalStageDefinitionValidator {
    private final StageStore stageStore;
    private final AcyclicValidator<ContextStageId> acyclicValidator;

    public FsStagesFSDValidator(StageStore stageStore, AcyclicValidator<ContextStageId> acyclicValidator) {
        this.stageStore = stageStore;
        this.acyclicValidator = acyclicValidator;
    }

    @Override
    public boolean validate(FunctionalStageDefinition definition) {
        Set<FsStage> functionalSubStages = definition.getFunctionalSubStages();
        if(functionalSubStages == null || functionalSubStages.isEmpty()){
            throw new RuntimeException("SubStages can't be null. It would mean the FunctionalStage does nothing.");
        }
        validateAllSubStageExist(functionalSubStages);
        Map<ContextStageId, Set<ContextStageId>> simplifiedIoDependencies = simplifiedIoDependencies(functionalSubStages);
        acyclicValidator.validate(simplifiedIoDependencies);

        return true;
    }

    private void validateAllSubStageExist(Set<FsStage> functionalSubStages) {
        for (FsStage functionalSubStage : functionalSubStages) {
            ContextStageId contextualizedId = functionalSubStage.getContextualizedId();
            String stageId = contextualizedId.getStageId();
            if(!this.stageStore.contains(stageId)){
                throw new RuntimeException("Stage with following Id not known: " + stageId);
            }
        }
    }

    private Map<ContextStageId, Set<ContextStageId>> simplifiedIoDependencies(Set<FsStage> flowStages) {
        return flowStages.stream().collect(Collectors.toMap(
                FsStage::getContextualizedId,
                flowStage -> flowStage.getIoDependencies().stream()
                        .map(ioDependency -> ioDependency.getInputProviders())
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .map(InputProvider::getFlowStageId)
                        .collect(Collectors.toSet())
        ));
    }
}
