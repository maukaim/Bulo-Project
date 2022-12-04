package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.ExecutionGraphDto;

public interface ExecutionGraphAdapter {
    ExecutionGraph adapte(ExecutionGraphDto dto);
}