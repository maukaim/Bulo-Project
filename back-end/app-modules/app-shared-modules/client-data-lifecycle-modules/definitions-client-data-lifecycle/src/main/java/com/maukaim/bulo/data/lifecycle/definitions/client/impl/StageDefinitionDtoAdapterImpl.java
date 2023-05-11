package com.maukaim.bulo.data.lifecycle.definitions.client.impl;

import com.maukaim.bulo.data.lifecycle.definitions.client.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.FunctionalSubStageDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.OutputProviderDtoAdapter;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.technical.TechnicalStageDefinitionDto;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StageDefinitionDtoAdapterImpl implements StageDefinitionDtoAdapter {
    private final ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter;
    private final StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter;
    private final StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter;
    private final FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter;
    private final OutputProviderDtoAdapter outputProviderDtoAdapter;

    public StageDefinitionDtoAdapterImpl(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
                                         StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                         StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter,
                                         FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter,
                                         OutputProviderDtoAdapter outputProviderDtoAdapter) {
        this.parameterDefinitionDtoAdapter = parameterDefinitionDtoAdapter;
        this.stageInputDefinitionDtoAdapter = stageInputDefinitionDtoAdapter;
        this.stageOutputDefinitionDtoAdapter = stageOutputDefinitionDtoAdapter;
        this.functionalSubStageDtoAdapter = functionalSubStageDtoAdapter;
        this.outputProviderDtoAdapter = outputProviderDtoAdapter;
    }

    @Override
    public StageDefinitionDto adapte(StageDefinition definition) {
        return switch (definition.getStageDefinitionType()){
            case FUNCTIONAL -> adapte((FunctionalStageDefinition)definition);
            case TECHNICAL -> adapte((TechnicalStageDefinition)definition);
        };

    }

    private TechnicalStageDefinitionDto adapte(TechnicalStageDefinition definition){
        return new TechnicalStageDefinitionDto(
                definition.getDefinitionId(),
                resolveInputs(definition.getInputsByName()),
                resolveOutputs(definition.getOutputsByName()),
                resolveParameters(definition.getParameters())
        );
    }

    private FunctionalStageDefinitionDto adapte(FunctionalStageDefinition definition) {
        return new FunctionalStageDefinitionDto(
                definition.getDefinitionId(),
                resolveInputs(definition.getInputsByName()),
                resolveOutputs(definition.getOutputsByName()),
                resolveParameters(definition.getParameters()),
                resolveFsStages(definition.getFunctionalSubStages()),
                resolveOutputProviders(definition.getOutputProviders())
                );
    }

    private Set<OutputProviderDto> resolveOutputProviders(Set<OutputProvider> outputProviders) {
        return outputProviders == null ? Set.of() : outputProviders.stream()
                .map(this.outputProviderDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private Set<FsStageDto> resolveFsStages(Set<FsStage> functionalSubStages) {
        return functionalSubStages == null ? Set.of() : functionalSubStages.stream()
                .map(this.functionalSubStageDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private List<ParameterDefinitionDto> resolveParameters(List<ParameterDefinition> parameter) {
        return parameter == null ? List.of() : parameter.stream()
                .map(this.parameterDefinitionDtoAdapter::adapte)
                .collect(Collectors.toList());
    }

    private Map<String, StageInputDefinitionDto> resolveInputs(Map<String, StageInputDefinition> inputsByName) {
        return inputsByName == null ? Map.of() : inputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.stageInputDefinitionDtoAdapter.adapte(entry.getValue())
                ));
    }

    private Map<String, StageOutputDefinitionDto> resolveOutputs(Map<String, StageOutputDefinition> outputsByName) {
        return outputsByName == null ? Map.of() : outputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.stageOutputDefinitionDtoAdapter.adapte(entry.getValue())
                ));
    }
}
