package com.maukaim.bulo.executors.data;

import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.executors.data.runs.ExecutionCancelledException;

import java.util.Map;

public interface StageRunner {
    StageDefinition getDefinition();

    Map<String, String> run (Map<String, String> inputs, Map<String, String> parameters) throws ExecutionCancelledException;
}
