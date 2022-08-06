package com.maukaim.bulo.stages.core.stage;

import com.maukaim.bulo.io.stages.FunctionalStageData;
import com.maukaim.bulo.io.stages.TechnicalStageData;
import com.maukaim.bulo.stages.core.ParameterAdapter;
import com.maukaim.bulo.stages.core.StageAdapter;
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
    public TechnicalStage adapte(TechnicalStageData stageData) {
        return adapte(UUID.randomUUID().toString(), stageData);
    }

    @Override
    public FunctionalStage adapte(FunctionalStageData stageData) {
        return adapte(UUID.randomUUID().toString(), stageData);
    }

    @Override
    public TechnicalStage adapte(String stageId, TechnicalStageData data) {
        return new TechnicalStage(
                stageId,
                data.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList()),
                data.getDefinitionId()
        );
    }

    @Override
    public FunctionalStage adapte(String stageId, FunctionalStageData data) {
        return new FunctionalStage(
                UUID.randomUUID().toString(),
                data.getParameters().stream().map(parameterAdapter::adapte).collect(Collectors.toList())
        );
    }
}
