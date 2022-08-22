package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.models.StageDefinition;

import java.util.Map;

public interface StageRunner {
    StageDefinition getDefinition();

    Map<String, String> run (Map<String, String> inputs, Map<String, String> parameters);
}
