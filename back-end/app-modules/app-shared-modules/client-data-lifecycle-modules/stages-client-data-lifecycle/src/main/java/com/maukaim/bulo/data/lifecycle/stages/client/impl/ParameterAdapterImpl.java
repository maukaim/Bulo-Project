package com.maukaim.bulo.data.lifecycle.stages.client.impl;

import com.maukaim.bulo.data.lifecycle.stages.client.ParameterAdapter;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;
import com.maukaim.bulo.stages.models.stage.Parameter;

public class ParameterAdapterImpl implements ParameterAdapter {
    @Override
    public Parameter adapte(ParameterDto dto) {
        return new Parameter(
                dto.getValue(),
                dto.getName(),
                dto.getAdditionalDetails()
        );
    }
}
