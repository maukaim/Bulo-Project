package com.maukaim.bulo.data.lifecycle.stages.client.impl;

import com.maukaim.bulo.data.lifecycle.stages.client.ParameterDtoAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.StageDtoAdapter;
import com.maukaim.bulo.io.stages.client.model.FunctionalStageDto;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

import java.util.stream.Collectors;

public class StageDtoAdapterImpl implements StageDtoAdapter {
    private final ParameterDtoAdapter parameterDtoAdapter;

    public StageDtoAdapterImpl(ParameterDtoAdapter parameterDtoAdapter) {
        this.parameterDtoAdapter = parameterDtoAdapter;
    }

    @Override
    public StageDto adapte(Stage stage) {
        return switch (stage.getStageType()) {
            case TECHNICAL -> adapteTechnicalStage((TechnicalStage) stage);
            case FUNCTIONAL -> adapteFunctionalStage((FunctionalStage) stage);
            default -> throw new IllegalStateException("Unexpected value: " + stage.getStageType());
        };
    }

    private FunctionalStageDto adapteFunctionalStage(FunctionalStage stage) {
        return new FunctionalStageDto(
                stage.getStageId(),
                stage.getParameters().stream()
                        .map(parameterDtoAdapter::adapte)
                        .collect(Collectors.toList()),
                stage.getDefinitionId()
        );
    }

    private TechnicalStageDto adapteTechnicalStage(TechnicalStage stage) {
        return new TechnicalStageDto(
                stage.getStageId(),
                stage.getParameters().stream()
                        .map(parameterDtoAdapter::adapte)
                        .collect(Collectors.toList()),
                stage.getDefinitionId()
        );
    }
}
