package com.maukaim.bulo.executors.data.stages;

import java.util.List;

public class Stage {
    private String stageId;
    private String definitionId;
    protected List<Parameter> parameters;

    public Stage(String stageId, List<Parameter> parameters, String definitionId) {
        this.stageId = stageId;
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
        return "Stage{" +
                "stageId='" + stageId + '\'' +
                ", definitionId='" + definitionId + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
