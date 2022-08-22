package com.maukaim.bulo.executor.data.management.adapters;


import com.maukaim.bulo.executor.core.api.models.StageDefinition;
import com.maukaim.bulo.executor.io.out.model.StageDefinitionDto;

import java.util.stream.Collectors;

public class StageDefinitionDtoDtoAdapterImpl implements StageDefinitionDtoAdapter {
    private final StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter;
    private final StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter;
    private final ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter;

    public StageDefinitionDtoDtoAdapterImpl(StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                            StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter,
                                            ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter) {
        this.stageInputDefinitionDtoAdapter = stageInputDefinitionDtoAdapter;
        this.stageOutputDefinitionDtoAdapter = stageOutputDefinitionDtoAdapter;
        this.parameterDefinitionDtoAdapter = parameterDefinitionDtoAdapter;
    }

    @Override
    public StageDefinitionDto adapte(StageDefinition definition) {
        return new StageDefinitionDto(
                definition.getTechnicalStageDefinitionId(),
                definition.getInputsByName()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry-> entry.getKey(),
                                entry-> this.stageInputDefinitionDtoAdapter.adapte(entry.getValue())
                        )),
                definition.getOutputsByName()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry-> entry.getKey(),
                                entry-> this.stageOutputDefinitionDtoAdapter.adapte(entry.getValue())
                        )),
                definition.getParameters().stream()
                        .map(this.parameterDefinitionDtoAdapter::adapte)
                        .collect(Collectors.toList())
        );
    }
}
