package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

public interface FlowStageDependencyDtoAdapter {
    FlowStageDependencyDto adapte(FlowStageDependency flowStageDependency);
}
