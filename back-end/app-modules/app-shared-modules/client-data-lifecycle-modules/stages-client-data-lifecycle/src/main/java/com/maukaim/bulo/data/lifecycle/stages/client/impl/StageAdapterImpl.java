package com.maukaim.bulo.data.lifecycle.stages.client.impl;

import com.maukaim.bulo.data.lifecycle.stages.client.ParameterAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.StageAdapter;
import com.maukaim.bulo.io.stages.client.model.FunctionalStageDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

import java.util.UUID;
import java.util.stream.Collectors;

public class StageAdapterImpl implements StageAdapter {
    private final ParameterAdapter parameterAdapter;

    public StageAdapterImpl(ParameterAdapter parameterAdapter) {
        this.parameterAdapter = parameterAdapter;
    }

    @Override
    public TechnicalStage adapte(TechnicalStageDto dto) {
        return adapte(UUID.randomUUID().toString(), dto);
    }

    @Override
    public FunctionalStage adapte(FunctionalStageDto dto) {
        return adapte(UUID.randomUUID().toString(), dto);
    }

    @Override
    public TechnicalStage adapte(String stageId, TechnicalStageDto dto) {
        return new TechnicalStage(
                stageId,
                dto.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList()),
                dto.getDefinitionId()
        );
    }

    @Override
    public FunctionalStage adapte(String stageId, FunctionalStageDto dto) {
        return new FunctionalStage(
                stageId,
                dto.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList()),
                dto.getDefinitionId()
        );
    }
}
