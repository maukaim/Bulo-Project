package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.FlowDto;

public interface FlowDtoAdapter {
    FlowDto adapte(Flow flow);
}
