package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters;

import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;
import com.maukaim.bulo.runs.orchestrators.io.both.model.FlowRunDto;

public interface FlowRunAdapter {
    FlowRun adapte(FlowRunDto flowRun);
}
