package com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages.impl;


import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages.ParameterAdapter;
import com.maukaim.bulo.definitions.data.stage.Parameter;
import com.maukaim.bulo.io.definitions.stage.ParameterDto;

public class ParameterAdapterImpl implements ParameterAdapter {
    @Override
    public Parameter adapte(ParameterDto dto) {
        return new Parameter(
                dto.getValue(),
                dto.getName()
        );
    }
}
