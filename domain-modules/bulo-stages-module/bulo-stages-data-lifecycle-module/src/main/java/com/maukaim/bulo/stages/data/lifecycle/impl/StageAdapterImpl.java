package com.maukaim.bulo.stages.data.lifecycle.impl;

import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;
import com.maukaim.bulo.stages.data.lifecycle.ParameterAdapter;
import com.maukaim.bulo.stages.data.lifecycle.StageAdapter;

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
                UUID.randomUUID().toString(),
                dto.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList()),
                dto.getDefinitionId()
        );
    }
}
