package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowDto;

public interface FlowAdapter {
    Flow adapte(FlowDto flowDto);
}
