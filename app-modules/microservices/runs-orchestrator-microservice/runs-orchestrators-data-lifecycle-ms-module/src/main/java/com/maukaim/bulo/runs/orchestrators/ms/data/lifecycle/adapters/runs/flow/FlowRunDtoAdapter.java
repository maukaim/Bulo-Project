package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunDto;

public interface FlowRunDtoAdapter {
    FlowRunDto adapte(FlowRun flowRun);
}
