package com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.impl;


import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.ParameterAdapter;
import com.maukaim.bulo.definitions.data.stage.Parameter;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;

public class ParameterAdapterImpl implements ParameterAdapter {
    @Override
    public Parameter adapte(ParameterDto dto) {
        return new Parameter(
                dto.getValue(),
                dto.getName()
        );
    }
}
