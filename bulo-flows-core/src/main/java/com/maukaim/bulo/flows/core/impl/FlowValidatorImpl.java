package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.flows.core.*;
import com.maukaim.bulo.flows.core.util.FlowUtils;
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

//TODO: Rewrite validator
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

        try {
            new AcyclicValidator<>(simplifiedIoDependencies(flowStages)).validate();
        } catch (Throwable t) {
            throw new FlowValidationException("Acyclic test failed on dependency graph. Following reason: " + t.getMessage());
        }

        Set<FlowStageId> flowStageIds = FlowUtils.getAllFlowStageIds(flow);
        for (FlowStage flowStage : flowStages) {
            String stageId = flowStage.getFlowStageId().getGlobalStageId();
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

    private void technicalStageValidation(FlowStage flowStage, TechnicalStage stage) throws FlowValidationException {
        String definitionId = stage.getDefinitionId();
        StageDefinition definition = this.definitionService.getById(definitionId);
        if (definition == null) {
            throw new FlowValidationException(String.format(
                    "No definition for technical stage %s so we can't validate the Flow.", stage.getStageId()));
        }
        this.stageParameterValidator.validate(stage, definition);

        Set<IoDependency> ioDependencies = flowStage.getIoDependencies();
        this.stageInputValidator.validate(ioDependencies, definition);

        for (IoDependency ioDependency : ioDependencies) {
            String inputId = ioDependency.getInputId();
            Set<InputProvider> inputProviders = ioDependency.getInputProviders();
            Map<StageDefinition, Set<String>> verifiableStages = filterAndGetOnlyTechnicalStages(inputProviders);
            StageInputDefinition stageInputDefinition = definition.getInputsByName().get(inputId);
            this.flowStageIoValidator.validate(inputId, stageInputDefinition, verifiableStages);
        }

    }

    //TODO: perf issue
    private Map<StageDefinition, Set<String>> filterAndGetOnlyTechnicalStages(Collection<InputProvider> inputProviders) {
        Map<StageDefinition, Set<String>> result = new HashMap<>();
        for ( InputProvider inputProvider : inputProviders) {
            FlowStageId flowStageId = inputProvider.getFlowStageId();
            Stage stage = this.stageService.getById(flowStageId.getGlobalStageId());

            if (stage instanceof TechnicalStage) {
                String definitionId = ((TechnicalStage) stage).getDefinitionId();
                StageDefinition definition = this.definitionService.getById(definitionId);
                result.put(definition, inputProvider.getOutputIds());
            }
        }
        return result;
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

