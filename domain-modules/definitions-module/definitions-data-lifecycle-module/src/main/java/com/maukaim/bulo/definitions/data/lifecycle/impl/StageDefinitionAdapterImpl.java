package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.io.definitions.shared.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;
import com.maukaim.bulo.definitions.data.lifecycle.functional.FunctionalSubStageAdapter;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.ParameterDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageInputDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageOutputDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionAdapter;
import com.maukaim.bulo.io.definitions.shared.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.technical.TechnicalStageDefinitionDto;
import com.maukaim.bulo.definitions.data.lifecycle.functional.OutputProviderAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StageDefinitionAdapterImpl implements StageDefinitionAdapter {
    private final ParameterDefinitionAdapter parameterDefinitionAdapter;
    private final StageInputDefinitionAdapter stageInputDefinitionAdapter;
    private final StageOutputDefinitionAdapter stageOutputDefinitionAdapter;
    private final FunctionalSubStageAdapter functionalSubStageAdapter;
    private final OutputProviderAdapter outputProviderAdapter;

    public StageDefinitionAdapterImpl(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                      StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                      StageOutputDefinitionAdapter stageOutputDefinitionAdapter,
                                      FunctionalSubStageAdapter functionalSubStageAdapter,
                                      OutputProviderAdapter outputProviderAdapter) {
        this.parameterDefinitionAdapter = parameterDefinitionAdapter;
        this.stageInputDefinitionAdapter = stageInputDefinitionAdapter;
        this.stageOutputDefinitionAdapter = stageOutputDefinitionAdapter;
        this.functionalSubStageAdapter = functionalSubStageAdapter;
        this.outputProviderAdapter = outputProviderAdapter;
    }

    @Override
    public StageDefinition adapte(StageDefinitionDto dto) {
        return switch (dto.getStageDefinitionType()) {
            case FUNCTIONAL -> adapte((FunctionalStageDefinitionDto)dto);
            case TECHNICAL -> adapte((TechnicalStageDefinitionDto)dto);
        };
    }

    private TechnicalStageDefinition adapte(TechnicalStageDefinitionDto dto){
        return new TechnicalStageDefinition(
                dto.getDefinitionId(),
                resolveInputs(dto.getInputsByName()),
                resolveOutputs(dto.getOutputsByName()),
                resolveParameters(dto.getParameters())
        );
    }

    private FunctionalStageDefinition adapte (FunctionalStageDefinitionDto dto){
        return new FunctionalStageDefinition(
                dto.getDefinitionId(),
                resolveInputs(dto.getInputsByName()),
                resolveOutputs(dto.getOutputsByName()),
                resolveParameters(dto.getParameters()),
                resolveFunctionalSubStage(dto.getFunctionalSubStages()),
                resolveOutputProviders(dto.getOutputProviders()));
    }

    private Set<OutputProvider> resolveOutputProviders(Set<OutputProviderDto> outputProviders) {
        return outputProviders == null ? Set.of() : outputProviders.stream()
                .map(this.outputProviderAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private Set<FsStage> resolveFunctionalSubStage(Set<FsStageDto> functionalSubStages) {
        return functionalSubStages == null ? Set.of() : functionalSubStages.stream()
                .map(this.functionalSubStageAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private List<ParameterDefinition> resolveParameters(List<ParameterDefinitionDto> parameterDtos) {
        return parameterDtos == null ? List.of() : parameterDtos.stream()
                .map(this.parameterDefinitionAdapter::adapte)
                .collect(Collectors.toList());
    }

    private Map<String, StageInputDefinition> resolveInputs(Map<String, StageInputDefinitionDto> inputDtosByName) {
        return inputDtosByName == null ? Map.of() : inputDtosByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.stageInputDefinitionAdapter.adapte(entry.getValue())
                ));
    }

    private Map<String, StageOutputDefinition> resolveOutputs(Map<String, StageOutputDefinitionDto> outputsByName) {
        return outputsByName == null ? Map.of() : outputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.stageOutputDefinitionAdapter.adapte(entry.getValue())
                ));
    }
}
