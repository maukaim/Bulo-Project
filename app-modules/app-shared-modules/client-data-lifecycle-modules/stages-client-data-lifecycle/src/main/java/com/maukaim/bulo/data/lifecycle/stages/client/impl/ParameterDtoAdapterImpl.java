package com.maukaim.bulo.data.lifecycle.stages.client.impl;

import com.maukaim.bulo.data.lifecycle.stages.client.ParameterDtoAdapter;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;
import com.maukaim.bulo.stages.models.stage.Parameter;

public class ParameterDtoAdapterImpl implements ParameterDtoAdapter {
    @Override
    public ParameterDto adapte(Parameter parameter) {
        return new ParameterDto(
                parameter.getValue(),
                parameter.getName(),
                parameter.getAdditionalDetails()
        );
    }
}
