package com.maukaim.bulo.flows.data.models.definition;

import java.util.List;
import java.util.Map;

public class StageDefinition {
    private String stageDefinitionId;
    private Map<String, StageInputDefinition> inputsByName;
    private Map<String, StageOutputDefinition> outputsByName;
    private List<ParameterDefinition> parameters;

    public StageDefinition(String stageDefinitionId,
                           Map<String, StageInputDefinition> inputsByName,
                           Map<String, StageOutputDefinition> outputsByName,
                           List<ParameterDefinition> parameters) {
        this.stageDefinitionId = stageDefinitionId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
    }

    public String getStageDefinitionId() {
        return stageDefinitionId;
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
        return "StageDefinition{" +
                "stageDefinitionId='" + stageDefinitionId + '\'' +
                ", inputsByName=" + inputsByName +
                ", outputsByName=" + outputsByName +
                ", parameters=" + parameters +
                '}';
    }
}
