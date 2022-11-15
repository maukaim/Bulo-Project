package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FailureGraph;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FailureGraphDto;

public interface FailureGraphAdapter {
    FailureGraph adapte(FailureGraphDto dto);
}