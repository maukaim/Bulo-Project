package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.FlowRunDto;

public interface FlowRunAdapter {
    FlowRun adapte(FlowRunDto flowRun);
}
