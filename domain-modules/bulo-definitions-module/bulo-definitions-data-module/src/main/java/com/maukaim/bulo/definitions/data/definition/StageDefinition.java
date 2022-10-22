package com.maukaim.bulo.definitions.data.definition;

import java.util.List;
import java.util.Map;

public abstract class StageDefinition {
    protected final String id;
    protected final Map<String, StageInputDefinition> inputsByName;
    protected final Map<String, StageOutputDefinition> outputsByName;
    protected final List<ParameterDefinition> parameters;
    protected final StageDefinitionType stageDefinitionType;

    public StageDefinition(String id,
                           Map<String, StageInputDefinition> inputsByName,
                           Map<String, StageOutputDefinition> outputsByName,
                           List<ParameterDefinition> parameters, StageDefinitionType stageDefinitionType) {
        this.id = id;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
        this.stageDefinitionType = stageDefinitionType;
    }

    public String getDefinitionId() {
        return id;
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

    public StageDefinitionType getStageDefinitionType() {
        return stageDefinitionType;
    }
}
