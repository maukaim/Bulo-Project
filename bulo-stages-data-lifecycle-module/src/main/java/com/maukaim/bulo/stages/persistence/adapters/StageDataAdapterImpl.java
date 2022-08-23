package com.maukaim.bulo.stages.persistence.adapters;

import com.maukaim.bulo.io.stages.FunctionalStageData;
import com.maukaim.bulo.io.stages.StageData;
import com.maukaim.bulo.io.stages.TechnicalStageData;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

import java.util.stream.Collectors;

public class StageDataAdapterImpl implements StageDataAdapter {
    private final ParameterDataAdapter parameterDataAdapter;

    public StageDataAdapterImpl(ParameterDataAdapter parameterDataAdapter) {
        this.parameterDataAdapter = parameterDataAdapter;
    }

    @Override
    public StageData adapte(Stage stage) {
        return switch (stage.getStageType()) {
            case TECHNICAL -> adapteTechnicalStage((TechnicalStage) stage);
            case FUNCTIONAL -> adapteFunctionalStage((FunctionalStage) stage);
            default -> throw new IllegalStateException("Unexpected value: " + stage.getStageType());
        };
    }

    private FunctionalStageData adapteFunctionalStage(FunctionalStage stage) {
        return new FunctionalStageData(
                stage.getParameters().stream().map(parameterDataAdapter::adapte).collect(Collectors.toList())
        );
    }

    private TechnicalStageData adapteTechnicalStage(TechnicalStage stage) {
        return new TechnicalStageData(
                stage.getParameters().stream().map(parameterDataAdapter::adapte).collect(Collectors.toList()),
                stage.getDefinitionId()
        );
    }
}
