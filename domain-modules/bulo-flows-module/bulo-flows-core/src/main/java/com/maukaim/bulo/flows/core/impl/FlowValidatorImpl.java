package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.flows.core.*;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import com.maukaim.bulo.flows.data.models.stage.TechnicalStage;

import java.util.*;
import java.util.stream.Collectors;

public class FlowValidatorImpl implements FlowValidator {
    private final StageService stageService;
    private final DefinitionService definitionService;
    private final StageParameterValidator stageParameterValidator;
    private final FlowStageIoValidator flowStageIoValidator;
    private final StageInputValidator stageInputValidator;

    public FlowValidatorImpl(StageService stageService, DefinitionService definitionService, StageParameterValidator stageParameterValidator, FlowStageIoValidator flowStageIoValidator, StageInputValidator stageInputValidator) {
        this.stageService = stageService;
        this.definitionService = definitionService;
        this.stageParameterValidator = stageParameterValidator;
        this.flowStageIoValidator = flowStageIoValidator;
        this.stageInputValidator = stageInputValidator;
    }

    @Override
    public boolean validate(Flow flow) throws FlowValidationException {
        Set<FlowStage> flowStages = flow.getFlowStages();
        if (flowStages == null || flowStages.isEmpty()) {
            throw new FlowValidationException("Dependency Graph can't be null or empty");
        }

        Map<FlowStageId, Set<FlowStageId>> simplifiedIoDependencies = simplifiedIoDependencies(flowStages);
        try {
            new AcyclicValidator<>(simplifiedIoDependencies).validate();
        } catch (Throwable t) {
            throw new FlowValidationException("Acyclic test failed on dependency graph. Following reason: " + t.getMessage());
        }
        validateAllAncestorsArePresent(simplifiedIoDependencies);
        for (FlowStage flowStage : flowStages) {
            String stageId = flowStage.getFlowStageId().getStageId();
            Stage stage = this.stageService.getById(stageId);
            if (stage == null) {
                throw new FlowValidationException("A Stage Id is not recognized, so we can't validate the Flow: " + stageId);
            }

            if (stage instanceof TechnicalStage) {
                technicalStageValidation(flowStage, (TechnicalStage) stage);
            }
        }
        return true;
    }

    private void validateAllAncestorsArePresent(Map<FlowStageId, Set<FlowStageId>> simplifiedIoDependencies) throws FlowValidationException {
        for (Map.Entry<FlowStageId, Set<FlowStageId>> entry : simplifiedIoDependencies.entrySet()) {
            Set<FlowStageId> ancestors = entry.getValue();
            for (FlowStageId ancestorId : ancestors) {
                if(!simplifiedIoDependencies.containsKey(ancestorId)){
                    throw new FlowValidationException(String.format(
                            "%s marked as ancestor of %s but not present itself in the Flow.",
                            ancestorId, entry.getKey())
                    );
                }
            }
        }
    }

    private void technicalStageValidation(FlowStage flowStage, TechnicalStage stage) throws FlowValidationException {
        String definitionId = stage.getDefinitionId();
        StageDefinition stagDefinition = this.definitionService.getById(definitionId);
        if (stagDefinition == null) {
            throw new FlowValidationException(String.format(
                    "No definition for technical stage %s so we can't validate the Flow.", stage.getStageId()));
        }
        this.stageParameterValidator.validate(stage, stagDefinition);

        Set<IoDependency> ioDependencies = flowStage.getIoDependencies();
        this.stageInputValidator.validate(ioDependencies, stagDefinition);

        for (IoDependency ioDependency : ioDependencies) {
            String inputId = ioDependency.getInputId();
            Set<InputProvider> inputProviders = ioDependency.getInputProviders();
            StageInputDefinition stageInputDefinition = stagDefinition.getInputsByName().get(inputId);
            this.flowStageIoValidator.validate(inputId, stageInputDefinition, inputProviders);
        }
    }

    private Map<FlowStageId, Set<FlowStageId>> simplifiedIoDependencies(Set<FlowStage> flowStages) {
        return flowStages.stream().collect(Collectors.toMap(
                FlowStage::getFlowStageId,
                flowStage -> flowStage.getIoDependencies().stream()
                        .map(ioDependency -> ioDependency.getInputProviders())
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .map(InputProvider::getFlowStageId)
                        .collect(Collectors.toSet())
        ));
    }
}

