package com.maukaim.boule.flows.orchestrator.stage;

import java.util.Map;
import java.util.Set;

//TODO: On devrait faire la diff entre StageModel (avec le StageType et les possibles parameters)
// Et les vrais instances de Stage (StageId, StageType, Parameters settled, etc) (Dans un Flow)
// Et les Instances de run?
public interface Stage {
    String getStageId();
    String getStageType();
    Map<String,String> getParameters();
}
