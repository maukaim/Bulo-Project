package com.maukaim.bulo.runners.embedded;

import com.maukaim.bulo.executors.data.models.ParameterDefinition;
import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.executors.data.models.StageInputDefinition;
import com.maukaim.bulo.executors.data.models.StageOutputDefinition;
import com.maukaim.bulo.executors.data.runs.ExecutionCancelledException;
import com.maukaim.bulo.runners.core.AbstractStageRunner;
import com.maukaim.bulo.runners.core.Marshaller;
import com.maukaim.bulo.runners.core.RunnerUtils;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class PrintYoloRunner extends AbstractStageRunner {
    private final static String DEF_ID = "PrintYolo";

    public PrintYoloRunner(Marshaller marshaller) {
        super(marshaller);
    }

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
    public Map<String, String> run(Map<String, String> inputs, Map<String, String> parameters) throws ExecutionCancelledException {
        String subject = getOrThrow(inputs, InputsProvider.SUBJECT_NAME, String.class);
        String isUpperCase = getOrThrow(parameters, ParametersProvider.UPPERCASE_NAME, String.class);
        String Greetings = getOrThrow(parameters, ParametersProvider.GREETINGS_NAME, String.class);

        System.out.println("Please let me sleep, just 30s...");
        try {
            Thread.sleep(15000);
            if (Instant.now().getEpochSecond() % 2 == 0) {
                System.out.println("Will cancel!");
                throw new ExecutionCancelledException();
            }
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new ExecutionCancelledException();
        }

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
        private final static Class<String> SUBJECT_TYPE = String.class;

        public static StageInputDefinition[] get() {
            return new StageInputDefinition[]{
                    StageInputDefinition.fromJava(SUBJECT_NAME, false, true, SUBJECT_TYPE)
            };
        }
    }

    private static class OutputsProvider {
        private final static String RESULT_NAME = "Yolo Result";
        private final static Class<String> RESULT_TYPE = String.class;

        public static StageOutputDefinition[] get() {
            return new StageOutputDefinition[]{
                    StageOutputDefinition.fromJava(RESULT_NAME, false, RESULT_TYPE)
            };
        }
    }

    private static class ParametersProvider {
        private final static String UPPERCASE_NAME = "Uppercase";
        private final static Class<Boolean> UPPERCASE_TYPE = Boolean.class;

        private final static String GREETINGS_NAME = "Greetings";
        private final static Class<String> GREETINGS_TYPE = String.class;

        public static ParameterDefinition[] get() {
            return new ParameterDefinition[]{
                    ParameterDefinition.fromJavaClass(UPPERCASE_NAME,
                            UPPERCASE_TYPE,
                            "true or false",
                            "Determines if the print should be uppercase or not.",
                            true),
                    ParameterDefinition.fromJavaClass(GREETINGS_NAME,
                            GREETINGS_TYPE,
                            "Hello, Ni hao, Bonjour, ...",
                            "Add greetings before Yolo result.",
                            true)
            };
        }
    }
}
