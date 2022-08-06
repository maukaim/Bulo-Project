package com.maukaim.bulo.stages.models.definition;

import com.maukaim.bulo.commons.models.TechnicalStageDefinitionInterface;

import java.util.List;

public class TechnicalStageDefinition implements TechnicalStageDefinitionInterface {
    private String id;
    private List<ParameterDefinition> parameters;

    public TechnicalStageDefinition(String id, List<ParameterDefinition> parameters) {
        this.id = id;
        this.parameters = parameters;
    }

    @Override
    public String getId() {
        return id;
    }

    public List<ParameterDefinition> getParameters() {
        return parameters;
    }
}
