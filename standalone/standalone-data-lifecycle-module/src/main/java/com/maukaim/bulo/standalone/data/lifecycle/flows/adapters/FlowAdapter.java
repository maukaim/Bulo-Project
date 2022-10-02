package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters;

import com.maukaim.bulo.flows.data.models.flow.Flow;

public interface FlowAdapter {
    com.maukaim.bulo.runs.orchestrators.data.flow.Flow adapte(Flow flow);
}
