package com.maukaim.bulo.definitions.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionalStageDefinition {
    private String id;
    private Map<String, StageInputDefinition> inputsByName;
    private Map<String, StageOutputDefinition> outputsByName;
    private List<ParameterDefinition> parameters;
    private final Set<FsStage> flowStages;

    public FunctionalStageDefinition(String technicalStageDefinitionId,
                                     Map<String, StageInputDefinition> inputsByName,
                                     Map<String, StageOutputDefinition> outputsByName,
                                     List<ParameterDefinition> parameters) {
        this.technicalStageDefinitionId = technicalStageDefinitionId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
    }

    public String getTechnicalStageDefinitionId() {
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
}