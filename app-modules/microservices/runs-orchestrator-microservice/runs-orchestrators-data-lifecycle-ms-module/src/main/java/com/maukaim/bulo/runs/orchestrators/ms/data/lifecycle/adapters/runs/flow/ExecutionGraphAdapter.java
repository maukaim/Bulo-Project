package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.ExecutionGraphDto;

public interface ExecutionGraphAdapter {
    ExecutionGraph adapte(ExecutionGraphDto dto);
}