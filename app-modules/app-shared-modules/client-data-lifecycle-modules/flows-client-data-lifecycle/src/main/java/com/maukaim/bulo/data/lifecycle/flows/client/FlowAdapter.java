package com.maukaim.bulo.data.lifecycle.flows.client;

import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.io.flows.client.model.FlowDto;

public interface FlowAdapter {
    Flow adapte (FlowDto dto);
}
