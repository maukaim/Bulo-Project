package com.maukaim.bulo.runs.orchestrators.app.data;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.flow.*;

import java.util.Set;

public class FakeContextProvider {
    public static final ContextStageId STAGE_1 = ContextStageId.of("STAGE_1");
    public static final ContextStageId STAGE_1_BIS = ContextStageId.of("STAGE_1", 1);
    public static final ContextStageId STAGE_2 = ContextStageId.of("STAGE_2");
    public static final ContextStageId STAGE_3 = ContextStageId.of("STAGE_3");
    public static final ContextStageId STAGE_4 = ContextStageId.of("STAGE_4");
    public static final ContextStageId STAGE_5 = ContextStageId.of("STAGE_5");
    public static final ContextStageId STAGE_6 = ContextStageId.of("STAGE_6");

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
