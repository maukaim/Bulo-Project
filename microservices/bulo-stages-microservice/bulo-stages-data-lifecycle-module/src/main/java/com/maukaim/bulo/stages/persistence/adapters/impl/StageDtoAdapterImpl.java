package com.maukaim.bulo.stages.persistence.adapters.impl;

import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;
import com.maukaim.bulo.stages.persistence.adapters.ParameterDataAdapter;
import com.maukaim.bulo.stages.persistence.adapters.StageDtoAdapter;

import java.util.stream.Collectors;

public class StageDtoAdapterImpl implements StageDtoAdapter {
    private final ParameterDataAdapter parameterDataAdapter;

    public StageDtoAdapterImpl(ParameterDataAdapter parameterDataAdapter) {
        this.parameterDataAdapter = parameterDataAdapter;
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
                stage.getParameters().stream().map(parameterDataAdapter::adapte).collect(Collectors.toList())
        );
    }

    private TechnicalStageDto adapteTechnicalStage(TechnicalStage stage) {
        return new TechnicalStageDto(
                stage.getParameters().stream().map(parameterDataAdapter::adapte).collect(Collectors.toList()),
                stage.getDefinitionId()
        );
    }
}
