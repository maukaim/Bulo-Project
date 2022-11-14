package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.FailureAlternativeRoute;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.FailureAlternativeRouteAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FailureAlternativeRoutesDto;

public class FailureAlternativeRouteAdapterImpl implements FailureAlternativeRouteAdapter {
    @Override
    public FailureAlternativeRoute adapte(FailureAlternativeRoutesDto dto) {
        return new FailureAlternativeRoute(
                dto.getFrom(),
                dto.getTo(),
                dto.getMaxUsage()
        );
    }
}
