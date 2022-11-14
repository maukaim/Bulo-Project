package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.FailureAlternativeRoute;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.FailureAlternativeRouteDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FailureAlternativeRoutesDto;

public class FailureAlternativeRouteDtoAdapterImpl implements FailureAlternativeRouteDtoAdapter {
    @Override
    public FailureAlternativeRoutesDto adapte(FailureAlternativeRoute failureAlternativeRoute) {
        return new FailureAlternativeRoutesDto(failureAlternativeRoute.getFrom(),
                failureAlternativeRoute.getTo(),
                failureAlternativeRoute.getMaxUsage());
    }
}
