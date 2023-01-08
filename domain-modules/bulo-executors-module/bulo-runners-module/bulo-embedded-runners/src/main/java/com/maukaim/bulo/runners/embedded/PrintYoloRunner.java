package com.maukaim.bulo.runners.embedded;

import com.maukaim.bulo.api.data.types.natives.BooleanType;
import com.maukaim.bulo.api.data.types.natives.StringType;
import com.maukaim.bulo.runners.api.StageRunner;
import com.maukaim.bulo.runners.api.StageRunnerContext;
import com.maukaim.bulo.runners.api.models.ParameterDefinition;
import com.maukaim.bulo.runners.api.models.StageDefinition;
import com.maukaim.bulo.runners.api.models.StageInputDefinition;
import com.maukaim.bulo.runners.api.models.StageOutputDefinition;
import com.maukaim.bulo.runners.api.ExecutionCancelledException;
import com.maukaim.bulo.runners.api.RunnerUtils;

import java.util.List;
import java.util.Map;

public class PrintYoloRunner implements StageRunner {
    private final static String DEF_ID = "PrintYolo";

    @Override
    public StageDefinition getDefinition() {
        return new StageDefinition(
                DEF_ID,
                RunnerUtils.toMap(InputsProvider.get()),
                RunnerUtils.toMap(OutputsProvider.get()),
                List.of(ParametersProvider.get())
        );
    }

    @Override
    public Map<String, String> run(StageRunnerContext ctx)throws ExecutionCancelledException {

        String subject = ctx.getRawInput(InputsProvider.SUBJECT_NAME);
        String isUpperCase = ctx.getRawParameter(ParametersProvider.UPPERCASE_NAME);
        String Greetings = ctx.getRawParameter(ParametersProvider.GREETINGS_NAME);

        System.out.println("No sleep for brave people..!");

        String yolo = (Greetings == null ? "" : Greetings) + " Yolo " + subject;
        yolo = isTrue(isUpperCase) ? yolo.toUpperCase() : yolo;
        System.out.println(yolo);

        return Map.of(OutputsProvider.RESULT_NAME, yolo);
    }

    private boolean isTrue(String isUpperCase) {
        return isUpperCase != null && "true".toLowerCase().equals(isUpperCase);
    }

    private static class InputsProvider {
        private final static String SUBJECT_NAME = "Yolo Subject";

        public static StageInputDefinition[] get() {
            return new StageInputDefinition[]{
                    new StageInputDefinition(SUBJECT_NAME,
                            StringType.required())
            };
        }
    }

    private static class OutputsProvider {
        private final static String RESULT_NAME = "Yolo Result";

        public static StageOutputDefinition[] get() {
            return new StageOutputDefinition[]{
                    new StageOutputDefinition(RESULT_NAME, StringType.required())
            };
        }
    }

    private static class ParametersProvider {
        private final static String UPPERCASE_NAME = "Uppercase";
        private final static String GREETINGS_NAME = "Greetings";

        public static ParameterDefinition[] get() {
            return new ParameterDefinition[]{
                    new ParameterDefinition(UPPERCASE_NAME,
                            BooleanType.required(),
                            "true or false",
                            "Determines if the print should be uppercase or not."
                    ),
                    new ParameterDefinition(GREETINGS_NAME,
                            StringType.required(),
                            "Hello, Ni hao, Bonjour, ...",
                            "Add greetings before Yolo result.")
            };
        }
    }
}
