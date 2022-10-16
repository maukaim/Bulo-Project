package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.io.flow.FlowDto;

public interface FlowAdapter {
    Flow adapte (FlowDto dto);
}
