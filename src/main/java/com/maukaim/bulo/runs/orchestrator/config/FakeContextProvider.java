package com.maukaim.bulo.runs.orchestrator.config;

import com.maukaim.bulo.runs.orchestrator.flow.view.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowView;
import com.maukaim.bulo.runs.orchestrator.flow.view.UnmodifiableAcyclicExecutionGraph;

import java.util.Map;
import java.util.Set;

public class FakeContextProvider {
    public static final FlowStageId STAGE_1 = FlowStageId.of("STAGE_1");
    public static final FlowStageId STAGE_2 = FlowStageId.of("STAGE_2");
    public static final FlowStageId STAGE_3 = FlowStageId.of("STAGE_3");
    public static final FlowStageId STAGE_4 = FlowStageId.of("STAGE_4");
    public static final FlowStageId STAGE_5 = FlowStageId.of("STAGE_5");
    public static final FlowStageId STAGE_6 = FlowStageId.of("STAGE_6");

    public static final FlowView FLOW_1 = new FlowView("FLOW_1",
            "Julien",
            "FOLAB",
            new UnmodifiableAcyclicExecutionGraph(
                    Map.of(
                            STAGE_1, Set.of(),
                            STAGE_2, Set.of(),
                            STAGE_3, Set.of(STAGE_1),
                            STAGE_4, Set.of(STAGE_1, STAGE_2),
                            STAGE_5, Set.of(STAGE_3, STAGE_2),
                            STAGE_6, Set.of(STAGE_3)
                    )),
            false);
}
