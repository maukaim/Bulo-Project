package com.maukaim.bulo.executors.core;

import java.util.Map;

public class StageRunConfig {
    private String stageRunId;
    private String definitionId;
    private Map<String, String> inputsByName;
    private Map<String, String> parametersByName;

    public StageRunConfig(String stageRunId, String definitionId, Map<String, String> inputsByName, Map<String, String> parametersByName) {
        this.stageRunId = stageRunId;
        this.definitionId = definitionId;
        this.inputsByName = inputsByName;
        this.parametersByName = parametersByName;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public Map<String, String> getInputsByName() {
        return inputsByName;
    }

    public Map<String, String> getParametersByName() {
        return parametersByName;
    }

    @Override
    public String toString() {
        return "StageRunConfig{" +
                "stageRunId='" + stageRunId + '\'' +
                ", definitionId='" + definitionId + '\'' +
                ", inputsByName=" + inputsByName +
                ", parametersByName=" + parametersByName +
                '}';
    }
}