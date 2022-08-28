package com.maukaim.bulo.flows.io.definition;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinitionDto {
    private String technicalStageDefinitionId;
    private Map<String, StageInputDefinitionDto> inputsByName;
    private Map<String, StageOutputDefinitionDto> outputsByName;
    private List<ParameterDefinitionDto> parameters;

    public TechnicalStageDefinitionDto(String technicalStageDefinitionId,
                                       Map<String, StageInputDefinitionDto> inputsByName,
                                       Map<String, StageOutputDefinitionDto> outputsByName,
                                       List<ParameterDefinitionDto> parameters) {
        this.technicalStageDefinitionId = technicalStageDefinitionId;
        this.inputsByName = inputsByName;
        this.outputsByName = outputsByName;
        this.parameters = parameters;
    }

    public String getTechnicalStageDefinitionId() {
        return technicalStageDefinitionId;
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