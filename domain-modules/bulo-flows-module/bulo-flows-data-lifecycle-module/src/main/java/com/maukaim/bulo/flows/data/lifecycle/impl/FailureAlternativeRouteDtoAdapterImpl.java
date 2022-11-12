package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.FailureAlternativeRouteDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.FailureAlternativeRoute;
import com.maukaim.bulo.flows.io.flow.FailureAlternativeRoutesDto;

public class FailureAlternativeRouteDtoAdapterImpl implements FailureAlternativeRouteDtoAdapter {
    @Override
    public FailureAlternativeRoutesDto adapte(FailureAlternativeRoute failureAlternativeRoute) {
        return new FailureAlternativeRoutesDto(
                failureAlternativeRoute.getFrom(),
                failureAlternativeRoute.getTo(),
                failureAlternativeRoute.getMaxUsage()
        );
    }
}
