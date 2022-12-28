package com.maukaim.bulo.runners.embedded;

import com.maukaim.bulo.api.data.types.natives.StringType;
import com.maukaim.bulo.executors.data.StageRunner;
import com.maukaim.bulo.executors.data.models.ParameterDefinition;
import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.executors.data.models.StageOutputDefinition;
import com.maukaim.bulo.executors.data.runs.ExecutionCancelledException;
import com.maukaim.bulo.runners.core.MissingInputException;
import com.maukaim.bulo.runners.core.RunnerUtils;

import java.util.List;
import java.util.Map;

//TODO: Add an ObjectMapper so it is able to translate inputs and Parameters

public class NameProvider implements StageRunner {
    private final static String DEF_ID = "NameProviding";

    @Override
    public StageDefinition getDefinition() {

        return new StageDefinition(
                DEF_ID,
                Map.of(),
                RunnerUtils.toMap(OutputsProvider.get()),
                List.of(ParametersProvider.get())
        );
    }

    @Override
    public Map<String, String> run(Map<String, String> inputs, Map<String, String> parameters) throws ExecutionCancelledException {
        String name = getOrThrow(parameters, ParametersProvider.PARAM_NAME);
        System.out.println("Will return name -> " + name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new ExecutionCancelledException();
        }

        return Map.of(OutputsProvider.RESULT_NAME, name);
    }

    private <T> T getOrThrow(Map<String, ? extends Object> inputs, String key) {
        if (inputs.containsKey(key)) {
            return (T) inputs.get(key);
        } else {
            throw new MissingInputException("Following input is missing: " + key);
        }
    }

    private static class OutputsProvider {
        private final static String RESULT_NAME = "Name";

        public static StageOutputDefinition[] get() {
            return new StageOutputDefinition[]{
                    new StageOutputDefinition(RESULT_NAME, StringType.required())
            };
        }
    }

    private static class ParametersProvider {
        private final static String PARAM_NAME = "Name";

        public static ParameterDefinition[] get() {
            return new ParameterDefinition[]{
                    new ParameterDefinition(PARAM_NAME,
                            StringType.required(),
                            "George, Fitzgerald, ...",
                            "Name to output.")
            };
        }
    }
}
