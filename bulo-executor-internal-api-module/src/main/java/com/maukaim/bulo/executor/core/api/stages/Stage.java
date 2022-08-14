package com.maukaim.bulo.executor.core.api.stages;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class Stage {
    protected List<Parameter> parameters;

    public Stage(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}
