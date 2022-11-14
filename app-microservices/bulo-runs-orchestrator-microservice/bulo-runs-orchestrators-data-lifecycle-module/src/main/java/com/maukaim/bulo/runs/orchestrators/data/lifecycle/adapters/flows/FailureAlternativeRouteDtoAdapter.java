package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows;


import com.maukaim.bulo.runs.orchestrators.data.flow.FailureAlternativeRoute;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FailureAlternativeRoutesDto;

public interface FailureAlternativeRouteDtoAdapter {
    FailureAlternativeRoutesDto adapte(FailureAlternativeRoute failureAlternativeRoute);
}
