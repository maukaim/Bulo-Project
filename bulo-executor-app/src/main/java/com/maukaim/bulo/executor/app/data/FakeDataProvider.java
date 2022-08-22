package com.maukaim.bulo.executor.app.data;

import com.maukaim.bulo.executor.core.api.stages.Parameter;
import com.maukaim.bulo.executor.core.api.stages.Stage;

import java.util.List;

public class FakeDataProvider {
    public static String STAGE_WITH_NO_INPUT_ID = "STAGE_WITH_NO_INPUT";
    public static Stage STAGE_WITH_NO_INPUT = new Stage(
            List.of(
                    new Parameter("Bobby",
                            "Name",
                            String.class.getName(),
                            "Not important details.")
            ),
            "NameProviding");

    public static String STAGE_WITH_INPUT_ID = "STAGE_WITH_INPUT";
    public static Stage STAGE_WITH_INPUT = new Stage(
            List.of(
                    new Parameter("true",
                            "Uppercase",
                            Boolean.class.getName(),
                            "Not important details."),
                    new Parameter("Hola Senior",
                            "Greetings",
                            String.class.getName(),
                            "Not important details.")
            ),
            "PrintYolo");

}
