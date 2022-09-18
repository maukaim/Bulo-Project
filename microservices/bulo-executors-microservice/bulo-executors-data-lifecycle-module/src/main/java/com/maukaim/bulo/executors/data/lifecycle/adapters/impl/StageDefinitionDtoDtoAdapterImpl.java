package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;


import com.maukaim.bulo.executors.data.lifecycle.adapters.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.executors.io.out.model.StageDefinitionDto;

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