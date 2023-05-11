package com.maukaim.bulo.data.lifecycle.stages.client;

import com.maukaim.bulo.io.stages.client.model.ParameterDto;
import com.maukaim.bulo.stages.models.stage.Parameter;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto dto);
}
