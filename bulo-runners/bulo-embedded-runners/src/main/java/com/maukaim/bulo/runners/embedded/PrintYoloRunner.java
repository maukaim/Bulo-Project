package com.maukaim.bulo.runners.embedded;

import com.maukaim.bulo.executor.core.api.*;
import com.maukaim.bulo.executor.core.api.models.ParameterDefinition;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageInputDefinition;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageOutputDefinition;
import com.maukaim.bulo.runners.core.MissingInputException;
import com.maukaim.bulo.runners.core.RunnerUtils;

import java.util.List;
import java.util.Map;

//TODO: Add an ObjectMapper so it is able to translate inputs and Parameters

public class PrintYoloRunner implements TechnicalStageRunner {
    private final static String DEF_ID = "PrintYolo";

    @Override
    public TechnicalStageDefinition getDefinition() {

        return new TechnicalStageDefinition(
                DEF_ID,
                RunnerUtils.toMap(InputsProvider.get()),
                RunnerUtils.toMap(OutputsProvider.get()),
                List.of(ParametersProvider.get())
        );
    }

    @Override
    public Map<String, String> run(Map<String, String> inputs, Map<String, String> parameters) {
        String subject = getOrThrow(inputs, InputsProvider.SUBJECT_NAME);
        Boolean isUpperCase = getOrThrow(inputs, ParametersProvider.UPPERCASE_NAME);
        String Greetings = getOrThrow(inputs, ParametersProvider.GREETINGS_NAME);
        String yolo = (Greetings == null ? "" : Greetings) + "Yolo" + subject;
        yolo = isUpperCase ? yolo.toUpperCase() : yolo;
        System.out.println(yolo);
        return Map.of(OutputsProvider.RESULT_NAME, yolo);
    }

    private <T> T getOrThrow(Map<String, ? extends Object> inputs, String key) {
        if (inputs.containsKey(key)) {
            return (T) inputs.get(key);
        } else {
            throw new MissingInputException("Following input is missing: " + key);
        }
    }

    private static class InputsProvider {
        private final static String SUBJECT_NAME = "Yolo Subject";
        private final static Class<String> SUBJECT_TYPE = String.class;

        public static TechnicalStageInputDefinition[] get() {
            return new TechnicalStageInputDefinition[]{
                    TechnicalStageInputDefinition.fromJava(SUBJECT_NAME, false, true, SUBJECT_TYPE)
            };
        }
    }

    private static class OutputsProvider {
        private final static String RESULT_NAME = "Yolo Result";
        private final static Class<String> RESULT_TYPE = String.class;

        public static TechnicalStageOutputDefinition[] get() {
            return new TechnicalStageOutputDefinition[]{
                    TechnicalStageOutputDefinition.fromJava(RESULT_NAME, false, RESULT_TYPE)
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
