package com.maukaim.bulo.flows.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.ParameterAdapter;
import com.maukaim.bulo.flows.data.models.stage.FunctionalStage;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import com.maukaim.bulo.flows.data.models.stage.TechnicalStage;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.io.stages.client.model.FunctionalStageDto;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;

import java.util.stream.Collectors;

public class StageAdapterImpl implements StageAdapter {
    private final ParameterAdapter parameterAdapter;

    public StageAdapterImpl(ParameterAdapter parameterAdapter) {
        this.parameterAdapter = parameterAdapter;
    }

    @Override
    public Stage adapte(StageDto dto) {
        return switch (dto.getStageType()) {
            case TECHNICAL -> adapteTechnicalStage((TechnicalStageDto) dto);
            case FUNCTIONAL -> adapteFunctionalStage((FunctionalStageDto) dto);
            default -> throw new IllegalStateException("Unexpected value: " + dto.getStageType());
        };
    }

    private FunctionalStage adapteFunctionalStage(FunctionalStageDto dto) {
        return new FunctionalStage(
                dto.getStageId(),
                dto.getDefinitionId(),
                dto.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList())
        );
    }

    private TechnicalStage adapteTechnicalStage(TechnicalStageDto dto) {
        return new TechnicalStage(
                dto.getStageId(),
                dto.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList()),
                dto.getDefinitionId()
        );
    }
}
