package com.maukaim.bulo.commons.io.instructions.models;

import java.util.List;
import java.util.Map;

public class StageDefinitionDto {
    protected final String definitionId;
    protected final Map<String, StageInputDefinitionDto> inputsByName;
    protected final Map<String, StageOutputDefinitionDto> outputsByName;
    protected final List<ParameterDefinitionDto> parameters;
    protected final StageDefinitionTypeDto stageDefinitionType;

    public StageDefinitionDto(String definitionId,
                              Map<String, StageInputDefinitionDto> inputsByName,
                              Map<String, StageOutputDefinitionDto> outputsByName,
                              List<ParameterDefinitionDto> parameters,
                              StageDefinitionTypeDto stageDefinitionType) {
        this.definitionId = definitionId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
        this.stageDefinitionType = stageDefinitionType;
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

    public StageDefinitionTypeDto getStageDefinitionType() {
        return stageDefinitionType;
    }
}
