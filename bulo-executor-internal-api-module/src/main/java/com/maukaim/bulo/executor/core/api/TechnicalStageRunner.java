package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;

import java.util.Map;

public interface TechnicalStageRunner {
    TechnicalStageDefinition getDefinition();

    Map<String, String> run (Map<String, String> inputs, Map<String, String> parameters);
}
