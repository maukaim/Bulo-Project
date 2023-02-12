package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

public interface FlowStageDependencyAdapter {
    ContextualizedStageDependency adapte(FlowStageDependencyDto dto);
}
