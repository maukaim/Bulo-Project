package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.functional.FsStageDto;
import com.maukaim.bulo.commons.io.instructions.models.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.functional.FunctionalSubStageAdapter;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.ParameterDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageInputDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageOutputDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionAdapter;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.technical.TechnicalStageDefinitionDto;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StageDefinitionAdapterImpl implements StageDefinitionAdapter {
    private final ParameterDefinitionAdapter parameterDefinitionAdapter;
    private final StageInputDefinitionAdapter stageInputDefinitionAdapter;
    private final StageOutputDefinitionAdapter stageOutputDefinitionAdapter;
    private final FunctionalSubStageAdapter functionalSubStageAdapter;

    public StageDefinitionAdapterImpl(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                      StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                      StageOutputDefinitionAdapter stageOutputDefinitionAdapter,
                                      FunctionalSubStageAdapter functionalSubStageAdapter) {
        this.parameterDefinitionAdapter = parameterDefinitionAdapter;
        this.stageInputDefinitionAdapter = stageInputDefinitionAdapter;
        this.stageOutputDefinitionAdapter = stageOutputDefinitionAdapter;
        this.functionalSubStageAdapter = functionalSubStageAdapter;
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
                resolveFunctionalSubStage(dto.getFunctionalSubStages())
        );
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