package com.maukaim.bulo.runs.orchestrators.app.data;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.flow.*;

import java.util.Set;

public class FakeContextProvider {
    public static final ContextualizedStageId STAGE_1 = ContextualizedStageId.of("STAGE_1");
    public static final ContextualizedStageId STAGE_1_BIS = ContextualizedStageId.of("STAGE_1", 1);
    public static final ContextualizedStageId STAGE_2 = ContextualizedStageId.of("STAGE_2");
    public static final ContextualizedStageId STAGE_3 = ContextualizedStageId.of("STAGE_3");
    public static final ContextualizedStageId STAGE_4 = ContextualizedStageId.of("STAGE_4");
    public static final ContextualizedStageId STAGE_5 = ContextualizedStageId.of("STAGE_5");
    public static final ContextualizedStageId STAGE_6 = ContextualizedStageId.of("STAGE_6");

    public static final Flow FLOW_1 = new Flow("FLOW_1",
            Set.of(new OwnerKey("Maukaim", OwnerKeyType.TEAM)),
            Set.of(
                    new FlowStage(STAGE_1, Set.of()),
                    new FlowStage(STAGE_2, Set.of()),
                    new FlowStage(STAGE_3, Set.of(
                            new InputDependency("INPUT_1", Set.of(
                                    new InputProvider(STAGE_1, Set.of("OUTPUT_1"))
                            ))
                    )),
                    new FlowStage(STAGE_1_BIS, Set.of(
                            new InputDependency("INPUT_1", Set.of(
                                    new InputProvider(STAGE_3, Set.of("OUTPUT_1"))
                            ))
                    )),
                    new FlowStage(STAGE_4, Set.of(
                            new InputDependency("INPUT_1", Set.of(
                                    new InputProvider(STAGE_1, Set.of("OUTPUT_1")),
                                    new InputProvider(STAGE_2, Set.of("OUTPUT_1"))
                            ))
                    )),
                    new FlowStage(STAGE_5, Set.of(
                            new InputDependency("INPUT_1", Set.of(
                                    new InputProvider(STAGE_3, Set.of("OUTPUT_1")),
                                    new InputProvider(STAGE_2, Set.of("OUTPUT_1"))
                            ))
                    )),
                    new FlowStage(STAGE_6, Set.of(
                            new InputDependency("INPUT_1", Set.of(
                                    new InputProvider(STAGE_3, Set.of("OUTPUT_1"))
                            ))
                    ))
            ),
            false);
}
