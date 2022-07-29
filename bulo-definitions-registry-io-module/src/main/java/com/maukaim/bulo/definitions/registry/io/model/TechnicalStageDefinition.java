package com.maukaim.bulo.definitions.registry.io.model;

import java.util.List;
import java.util.Map;

/**
 * J'ai besoin d'un identifiant du model
 * J'ai besoin de savoir les I/O
 * J'ai besoin de savoir les Parameters
 */
public class TechnicalStageDefinition {
    private String stageModelId;
    private Map<String, StageInputDefinition> inputsByName;
    private Map<String, StageOutputDefinition> outputsByName;
    private List<ParameterDefinition> parameters;

    public TechnicalStageDefinition(String stageModelId, Map<String, StageInputDefinition> inputsByName, Map<String, StageOutputDefinition> outputsByName, List<ParameterDefinition> parameters) {
        this.stageModelId = stageModelId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
    }

    public String getStageModelId() {
        return stageModelId;
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
