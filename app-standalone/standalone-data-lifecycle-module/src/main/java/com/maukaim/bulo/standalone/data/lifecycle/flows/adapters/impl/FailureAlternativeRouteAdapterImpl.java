package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.FailureAlternativeRoute;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.FailureAlternativeRouteAdapter;

public class FailureAlternativeRouteAdapterImpl implements FailureAlternativeRouteAdapter {
    @Override
    public FailureAlternativeRoute adapte(com.maukaim.bulo.flows.data.models.flow.FailureAlternativeRoute failureAlternativeRoute) {
        return new FailureAlternativeRoute(
                failureAlternativeRoute.getFrom(),
                failureAlternativeRoute.getTo(),
                failureAlternativeRoute.getMaxUsage()
        );
    }
}
