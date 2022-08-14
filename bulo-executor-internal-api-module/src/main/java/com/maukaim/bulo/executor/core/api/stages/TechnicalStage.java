package com.maukaim.bulo.executor.core.api.stages;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStage extends Stage {
    private String definitionId;

    public TechnicalStage(List<Parameter> parameters, String definitionId) {
        super(parameters);
        this.definitionId = definitionId;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    @Override
    public String toString() {
        return "TechnicalStageData{" +
                "definitionId='" + definitionId + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
