package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.core.FlowStageIoValidator;
import com.maukaim.bulo.flows.core.FlowValidationException;
import com.maukaim.bulo.flows.core.FlowValidator;
import com.maukaim.bulo.flows.core.StageInputValidator;
import com.maukaim.bulo.flows.core.StageParameterValidator;
import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.flows.data.models.stage.Stage;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class FlowValidatorImpl implements FlowValidator {
    private final StageService stageService;
    private final DefinitionService definitionService;
    private final StageParameterValidator stageParameterValidator;
    private final FlowStageIoValidator flowStageIoValidator;
    private final StageInputValidator stageInputValidator;
    private final AcyclicValidator<ContextStageId> acyclicValidator;

    public FlowValidatorImpl(StageService stageService,
                             DefinitionService definitionService,
                             StageParameterValidator stageParameterValidator,
                             FlowStageIoValidator flowStageIoValidator,
                             StageInputValidator stageInputValidator,
                             AcyclicValidator<ContextStageId> acyclicValidator) {
        this.stageService = stageService;
        this.definitionService = definitionService;
        this.stageParameterValidator = stageParameterValidator;
        this.flowStageIoValidator = flowStageIoValidator;
        this.stageInputValidator = stageInputValidator;
        this.acyclicValidator = acyclicValidator;
    }

    @Override
    public boolean validate(Flow flow) throws FlowValidationException {
        Set<FlowStage> flowStages = flow.getFlowStages();
        if (flowStages == null || flowStages.isEmpty()) {
            throw new FlowValidationException("Dependency Graph can't be null or empty");
        }

        Map<ContextStageId, Set<ContextStageId>> simplifiedIoDependencies = simplifiedIoDependencies(flowStages);
        try {
            acyclicValidator.validate(simplifiedIoDependencies);
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

            stageValidation(flowStage, stage);

        }
        return true;
    }

    private void validateAllAncestorsArePresent(Map<ContextStageId, Set<ContextStageId>> simplifiedIoDependencies) throws FlowValidationException {
        for (Map.Entry<ContextStageId, Set<ContextStageId>> entry : simplifiedIoDependencies.entrySet()) {
            Set<ContextStageId> ancestors = entry.getValue();
            for (ContextStageId ancestorId : ancestors) {
                if (!simplifiedIoDependencies.containsKey(ancestorId)) {
                    throw new FlowValidationException(String.format(
                            "%s marked as ancestor of %s but not present itself in the Flow.",
                            ancestorId, entry.getKey())
                    );
                }
            }
        }
    }

    private void stageValidation(FlowStage flowStage, Stage stage) throws FlowValidationException {
        String definitionId = stage.getDefinitionId();
        StageDefinition stagDefinition = this.definitionService.getById(definitionId);
        if (stagDefinition == null) {
            throw new FlowValidationException(String.format(
                    "No definition for stage %s so we can't validate the Flow.", stage.getStageId()));
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

    private Map<ContextStageId, Set<ContextStageId>> simplifiedIoDependencies(Set<FlowStage> flowStages) {
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

