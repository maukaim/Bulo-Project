package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters;

import com.maukaim.bulo.runs.orchestrators.data.flow.FailureAlternativeRoute;

public interface FailureAlternativeRouteAdapter {
    FailureAlternativeRoute adapte(com.maukaim.bulo.flows.data.models.flow.FailureAlternativeRoute failureAlternativeRoute);
}
