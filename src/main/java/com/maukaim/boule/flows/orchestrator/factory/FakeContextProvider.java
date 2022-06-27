package com.maukaim.boule.flows.orchestrator.factory;

import com.maukaim.boule.flows.orchestrator.stage.Stage;
import com.maukaim.boule.flows.orchestrator.flow.model.Flow;
import com.maukaim.boule.flows.orchestrator.flow.model.UnmodifiableAcyclicExecutionGraph;
import com.maukaim.boule.flows.orchestrator.stage.template.DummyStage;

import java.util.Map;
import java.util.Set;

public class FakeContextProvider {
    public static final Stage STAGE_1 = new DummyStage("STAGE_1");
    public static final Stage STAGE_2 = new DummyStage("STAGE_2");
    public static final Stage STAGE_3 = new DummyStage("STAGE_3");
    public static final Stage STAGE_4 = new DummyStage("STAGE_4");
    public static final Stage STAGE_5 = new DummyStage("STAGE_5");
    public static final Stage STAGE_6 = new DummyStage("STAGE_6");

    public static final Flow FLOW_1 = new Flow("FLOW_1",
            "Julien",
            "FOLAB",
            new UnmodifiableAcyclicExecutionGraph(
                    Map.of(
                            STAGE_1.getStageId(), Set.of(),
                            STAGE_2.getStageId(), Set.of(),
                            STAGE_3.getStageId(), Set.of(STAGE_1.getStageId()),
                            STAGE_4.getStageId(), Set.of(STAGE_1.getStageId(), STAGE_2.getStageId()),
                            STAGE_5.getStageId(), Set.of(STAGE_3.getStageId(), STAGE_2.getStageId()),
                            STAGE_6.getStageId(), Set.of(STAGE_3.getStageId())
                            )),
            false);
}
