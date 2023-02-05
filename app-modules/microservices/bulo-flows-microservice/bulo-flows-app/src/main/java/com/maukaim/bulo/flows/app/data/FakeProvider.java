package com.maukaim.bulo.flows.app.data;

import com.maukaim.bulo.api.data.types.natives.StringType;
import com.maukaim.bulo.flows.data.models.definition.ParameterDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.flows.data.models.stage.Parameter;
import com.maukaim.bulo.flows.data.models.stage.TechnicalStage;

import java.util.List;
import java.util.Map;

public class FakeProvider {

    public static StageDefinition STAGE_DEF_1 = new StageDefinition(
            "DEF_1",
            Map.of(),
            Map.of("output_1",new StageOutputDefinition(StringType.required())),
            List.of(new ParameterDefinition("Name",StringType.required()))
    );

    public static StageDefinition STAGE_DEF_2 = new StageDefinition(
            "DEF_2",
            Map.of("input_1",new StageInputDefinition( StringType.required())),
            Map.of("output_1",new StageOutputDefinition(StringType.required())),
            List.of(new ParameterDefinition("Naming", StringType.required()))
    );

    public static TechnicalStage STAGE_1 = new TechnicalStage(
            "STAGE_1",
            List.of(new Parameter("Bobby","Name")),
            "DEF_1"
    );

    public static TechnicalStage STAGE_2 = new TechnicalStage(
            "STAGE_2",
            List.of(new Parameter("Bobby","Naming")),
            "DEF_2"
    );


}
