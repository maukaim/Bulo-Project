package com.maukaim.bulo.flows.app.data;

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
            Map.of("output_1",new StageOutputDefinition(false,"stringage")),
            List.of(new ParameterDefinition("Name","string",true))
    );

    public static StageDefinition STAGE_DEF_2 = new StageDefinition(
            "DEF_2",
            Map.of("input_1",new StageInputDefinition(false,true,"stringage")),
            Map.of("output_1",new StageOutputDefinition(false,"string")),
            List.of(new ParameterDefinition("Naming","stringeuh",true))
    );

    public static TechnicalStage STAGE_1 = new TechnicalStage(
            "STAGE_1",
            List.of(new Parameter("Bobby","Name","string")),
            "DEF_1"
    );

    public static TechnicalStage STAGE_2 = new TechnicalStage(
            "STAGE_2",
            List.of(new Parameter("Bobby","Naming","stringeuh")),
            "DEF_2"
    );


}
