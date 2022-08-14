package com.maukaim.bulo.executor.data.management.adapters;


import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.io.out.model.TechnicalStageDefinitionDto;

import java.util.stream.Collectors;

public class TechnicalStageDefinitionDtoDtoAdapterImpl implements TechnicalStageDefinitionDtoAdapter {
    private final StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter;
    private final StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter;
    private final ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter;

    public TechnicalStageDefinitionDtoDtoAdapterImpl(StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
            StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter,
            ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter) {
        this.stageInputDefinitionDtoAdapter = stageInputDefinitionDtoAdapter;
        this.stageOutputDefinitionDtoAdapter = stageOutputDefinitionDtoAdapter;
        this.parameterDefinitionDtoAdapter = parameterDefinitionDtoAdapter;
    }

    @Override
    public TechnicalStageDefinitionDto adapte(TechnicalStageDefinition definition) {
        return new TechnicalStageDefinitionDto(
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
