package com.maukaim.bulo.runners.api;

import com.maukaim.bulo.runners.api.models.StageDefinition;

import java.util.Map;

public interface StageRunner {
    StageDefinition getDefinition();

    Map<String, String> run(StageRunnerContext ctx) throws ExecutionCancelledException;
}
