package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.FailureAlternativeRouteAdapter;
import com.maukaim.bulo.flows.data.models.flow.FailureAlternativeRoute;
import com.maukaim.bulo.flows.io.flow.FailureAlternativeRoutesDto;

public class FailureAlternativeRouteAdapterImpl implements FailureAlternativeRouteAdapter {
    @Override
    public FailureAlternativeRoute adapte(FailureAlternativeRoutesDto dto) {
        return new FailureAlternativeRoute(dto.getFrom(),
                dto.getTo(),
                dto.getMaxUsage());
    }
}
