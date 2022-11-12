package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.FailureAlternativeRoute;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.io.flow.FailureAlternativeRoutesDto;
import com.maukaim.bulo.flows.io.flow.FlowStageDto;

public interface FailureAlternativeRouteAdapter {
    FailureAlternativeRoute adapte(FailureAlternativeRoutesDto dto);
}
