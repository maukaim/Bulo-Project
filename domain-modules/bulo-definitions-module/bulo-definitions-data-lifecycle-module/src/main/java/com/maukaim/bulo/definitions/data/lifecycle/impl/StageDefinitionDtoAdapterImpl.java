package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.commons.io.instructions.models.functional.FsStageDto;
import com.maukaim.bulo.commons.io.instructions.models.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionDtoAdapter;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.technical.TechnicalStageDefinitionDto;
import com.maukaim.bulo.definitions.data.lifecycle.functional.FunctionalSubStageDtoAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StageDefinitionDtoAdapterImpl implements StageDefinitionDtoAdapter {
    private final ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter;
    private final StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter;
    private final StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter;
    private final FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter;

    public StageDefinitionDtoAdapterImpl(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
                                         StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                         StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter, FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter) {
        this.parameterDefinitionDtoAdapter = parameterDefinitionDtoAdapter;
        this.stageInputDefinitionDtoAdapter = stageInputDefinitionDtoAdapter;
        this.stageOutputDefinitionDtoAdapter = stageOutputDefinitionDtoAdapter;
        this.functionalSubStageDtoAdapter = functionalSubStageDtoAdapter;
    }

    @Override
    public com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto adapte(StageDefinition definition) {
        return switch (definition.getStageDefinitionType()){
            case FUNCTIONAL -> adapte((FunctionalStageDefinition)definition);
            case TECHNICAL -> adapte((TechnicalStageDefinition)definition);
        };

    }

    private TechnicalStageDefinitionDto adapte(TechnicalStageDefinition definition){
        return new TechnicalStageDefinitionDto(
                definition.getId(),
                resolveInputs(definition.getInputsByName()),
                resolveOutputs(definition.getOutputsByName()),
                resolveParameters(definition.getParameters())
        );
    }

    private FunctionalStageDefinitionDto adapte(FunctionalStageDefinition definition) {
        return new FunctionalStageDefinitionDto(
                definition.getId(),
                resolveInputs(definition.getInputsByName()),
                resolveOutputs(definition.getOutputsByName()),
                resolveParameters(definition.getParameters()),
                resolveFsStages(definition.getFunctionalSubStages())
        );
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
