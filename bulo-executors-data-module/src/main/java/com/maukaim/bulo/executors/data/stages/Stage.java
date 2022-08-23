package com.maukaim.bulo.executors.data.stages;

import java.util.List;

public class Stage {
    private String definitionId;
    protected List<Parameter> parameters;

    public Stage(List<Parameter> parameters, String definitionId) {
        this.parameters = parameters;
        this.definitionId = definitionId;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "TechnicalStageData{" +
                "definitionId='" + definitionId + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
