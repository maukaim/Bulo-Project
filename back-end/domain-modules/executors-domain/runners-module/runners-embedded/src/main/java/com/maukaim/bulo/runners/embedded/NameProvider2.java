package com.maukaim.bulo.runners.embedded;

import com.maukaim.bulo.api.data.types.natives.StringType;
import com.maukaim.bulo.api.data.types.parameters.ArrayParameterType;
import com.maukaim.bulo.api.data.types.parameters.impl.VoitureType;
import com.maukaim.bulo.runners.api.StageRunner;
import com.maukaim.bulo.runners.api.StageRunnerContext;
import com.maukaim.bulo.runners.api.models.ParameterDefinition;
import com.maukaim.bulo.runners.api.models.StageDefinition;
import com.maukaim.bulo.runners.api.models.StageOutputDefinition;
import com.maukaim.bulo.runners.api.ExecutionCancelledException;
import com.maukaim.bulo.runners.api.RunnerUtils;

import java.util.List;
import java.util.Map;

public class NameProvider2 implements StageRunner {
    private final static String DEF_ID = "NameProviding2";

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
    public Map<String, String> run(StageRunnerContext ctx) throws ExecutionCancelledException {
        String name = ctx.getRawParameter(ParametersProvider.PARAM_NAME);
        System.out.println("Will return name -> " + name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new ExecutionCancelledException();
        }

        return Map.of(OutputsProvider.RESULT_NAME, name);
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
                            new ArrayParameterType(new ArrayParameterType(VoitureType.required(), true), true),
                            "George, Fitzgerald, ...",
                            "Name to output.")
            };
        }
    }
}
