package com.maukaim.bulo.stages.models.definition;

import com.maukaim.bulo.commons.models.StageDefinitionInterface;

import java.util.List;

public class StageDefinition implements StageDefinitionInterface {
    private String id;
    private List<ParameterDefinition> parameters;

    public StageDefinition(String id, List<ParameterDefinition> parameters) {
        this.id = id;
        this.parameters = parameters;
    }

    @Override
    public String getDefinitionId() {
        return id;
    }

    public List<ParameterDefinition> getParameters() {
        return parameters;
    }
}
