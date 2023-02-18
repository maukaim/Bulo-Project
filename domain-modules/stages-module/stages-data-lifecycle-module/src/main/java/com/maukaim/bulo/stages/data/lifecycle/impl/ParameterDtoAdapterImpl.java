package com.maukaim.bulo.stages.data.lifecycle.impl;

import com.maukaim.bulo.io.stages.client.model.ParameterDto;
import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.stages.data.lifecycle.ParameterDtoAdapter;

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
