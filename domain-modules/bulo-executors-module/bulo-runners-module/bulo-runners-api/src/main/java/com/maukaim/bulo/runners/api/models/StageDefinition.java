package com.maukaim.bulo.runners.api.models;

import java.util.List;
import java.util.Map;

public class StageDefinition {
    private String technicalStageDefinitionId;
    private Map<String, StageInputDefinition> inputsByName;
    private Map<String, StageOutputDefinition> outputsByName;
    private List<ParameterDefinition> parameters;

    public StageDefinition(String technicalStageDefinitionId,
                           Map<String, StageInputDefinition> inputsByName,
                           Map<String, StageOutputDefinition> outputsByName,
                           List<ParameterDefinition> parameters) {
        this.technicalStageDefinitionId = technicalStageDefinitionId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
    }

    public String getDefinitionId() {
        return technicalStageDefinitionId;
    }

    public Map<String, StageInputDefinition> getInputsByName() {
        return inputsByName;
    }

    public Map<String, StageOutputDefinition> getOutputsByName() {
        return outputsByName;
    }

    public List<ParameterDefinition> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "TechnicalStageDefinition{" +
                "technicalStageDefinitionId='" + technicalStageDefinitionId + '\'' +
                ", inputsByName=" + inputsByName +
                ", outputsByName=" + outputsByName +
                ", parameters=" + parameters +
                '}';
    }
}