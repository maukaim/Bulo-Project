package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.io.stages.ParameterData;
import com.maukaim.bulo.stages.models.stage.Parameter;

public interface ParameterAdapter {
    Parameter adapte(ParameterData data);
}
