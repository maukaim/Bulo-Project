package com.maukaim.bulo.executor.core.api.models;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinition {
    private String technicalStageDefinitionId;
    private Map<String, TechnicalStageInputDefinition> inputsByName;
    private Map<String, TechnicalStageOutputDefinition> outputsByName;
    private List<ParameterDefinition> parameters;

    public TechnicalStageDefinition(String technicalStageDefinitionId,
                                    Map<String, TechnicalStageInputDefinition> inputsByName,
                                    Map<String, TechnicalStageOutputDefinition> outputsByName,
                                    List<ParameterDefinition> parameters) {
        this.technicalStageDefinitionId = technicalStageDefinitionId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
    }

    public String getTechnicalStageDefinitionId() {
        return technicalStageDefinitionId;
    }

    public Map<String, TechnicalStageInputDefinition> getInputsByName() {
        return inputsByName;
    }

    public Map<String, TechnicalStageOutputDefinition> getOutputsByName() {
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