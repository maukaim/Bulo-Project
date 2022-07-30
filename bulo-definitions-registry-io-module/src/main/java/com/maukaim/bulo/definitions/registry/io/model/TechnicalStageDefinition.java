package com.maukaim.bulo.definitions.registry.io.model;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinition {
    private String technicalStageId;
    private Map<String, StageInputDefinition> inputsByName;
    private Map<String, StageOutputDefinition> outputsByName;
    private List<ParameterDefinition> parameters;

    public TechnicalStageDefinition(String technicalStageId,
                                    Map<String, StageInputDefinition> inputsByName,
                                    Map<String, StageOutputDefinition> outputsByName,
                                    List<ParameterDefinition> parameters) {
        this.technicalStageId = technicalStageId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
    }

    public String getTechnicalStageId() {
        return technicalStageId;
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
}