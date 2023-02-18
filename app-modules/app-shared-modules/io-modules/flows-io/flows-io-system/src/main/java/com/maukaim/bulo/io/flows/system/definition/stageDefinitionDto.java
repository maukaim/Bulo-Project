package com.maukaim.bulo.io.flows.system.definition;

import java.util.List;
import java.util.Map;

public class stageDefinitionDto {
    private String definitionId;
    private Map<String, StageInputDefinitionDto> inputsByName;
    private Map<String, StageOutputDefinitionDto> outputsByName;
    private List<ParameterDefinitionDto> parameters;

    public stageDefinitionDto(String definitionId,
                              Map<String, StageInputDefinitionDto> inputsByName,
                              Map<String, StageOutputDefinitionDto> outputsByName,
                              List<ParameterDefinitionDto> parameters) {
        this.definitionId = definitionId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public Map<String, StageInputDefinitionDto> getInputsByName() {
        return inputsByName;
    }

    public Map<String, StageOutputDefinitionDto> getOutputsByName() {
        return outputsByName;
    }

    public List<ParameterDefinitionDto> getParameters() {
        return parameters;
    }
}