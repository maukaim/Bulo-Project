package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.executors.data.lifecycle.adapters.ParameterAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.executors.data.stages.Stage;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;

import java.util.List;

public class StageAdapterImpl implements StageAdapter {
    private final ParameterAdapter parameterAdapter;

    public StageAdapterImpl(ParameterAdapter parameterAdapter) {
        this.parameterAdapter = parameterAdapter;
    }

    @Override
    public Stage adapte(TechnicalStageDto dto) {
        return new Stage(
                dto.getStageId(),
                resolve(dto.getParameters()),
                dto.getDefinitionId()
        );
    }

    private List<Parameter> resolve(List<ParameterDto> parameters) {
        return parameters == null ? null : parameters.stream()
                .map(this.parameterAdapter::adapte)
                .toList();
    }
}
