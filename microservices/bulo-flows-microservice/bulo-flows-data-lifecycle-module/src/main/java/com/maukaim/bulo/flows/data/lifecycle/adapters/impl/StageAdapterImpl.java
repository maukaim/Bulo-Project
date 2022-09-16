package com.maukaim.bulo.flows.data.lifecycle.adapters.impl;


import com.maukaim.bulo.flows.data.lifecycle.adapters.ParameterAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.flows.data.models.stage.FunctionalStage;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import com.maukaim.bulo.flows.data.models.stage.TechnicalStage;
import com.maukaim.bulo.flows.io.stage.FunctionalStageDto;
import com.maukaim.bulo.flows.io.stage.StageDto;
import com.maukaim.bulo.flows.io.stage.TechnicalStageDto;

import java.util.stream.Collectors;

public class StageAdapterImpl implements StageAdapter {
    private final ParameterAdapter parameterAdapter;

    public StageAdapterImpl(ParameterAdapter parameterAdapter) {
        this.parameterAdapter = parameterAdapter;
    }

    @Override
    public Stage adapte(String stageId, StageDto dto) {
        return switch (dto.getStageType()) {
            case TECHNICAL -> adapteTechnicalStage(stageId, (TechnicalStageDto) dto);
            case FUNCTIONAL -> adapteFunctionalStage(stageId, (FunctionalStageDto) dto);
            default -> throw new IllegalStateException("Unexpected value: " + dto.getStageType());
        };
    }

    private FunctionalStage adapteFunctionalStage(String stageId, FunctionalStageDto dto) {
        return new FunctionalStage(
                stageId,
                dto.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList())
        );
    }

    private TechnicalStage adapteTechnicalStage(String stageId, TechnicalStageDto dto) {
        return new TechnicalStage(
                stageId,
                dto.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList()),
                dto.getDefinitionId()
        );
    }
}
