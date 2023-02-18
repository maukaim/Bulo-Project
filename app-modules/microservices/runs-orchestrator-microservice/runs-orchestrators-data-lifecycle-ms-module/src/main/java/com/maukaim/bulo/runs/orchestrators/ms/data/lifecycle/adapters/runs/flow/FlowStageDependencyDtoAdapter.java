package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowStageDependencyDto;

public interface FlowStageDependencyDtoAdapter {
    FlowStageDependencyDto adapte(ContextualizedStageDependency contextualizedStageDependency);
}
