package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.io.flows.client.model.FlowDto;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;

public interface FlowDtoAdapter {
    FlowDto adapte(Flow flow);
}
