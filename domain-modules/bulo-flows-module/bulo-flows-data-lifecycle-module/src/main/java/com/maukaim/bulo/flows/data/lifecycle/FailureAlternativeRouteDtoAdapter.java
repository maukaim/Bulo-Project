package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.FailureAlternativeRoute;
import com.maukaim.bulo.flows.io.flow.FailureAlternativeRoutesDto;

public interface FailureAlternativeRouteDtoAdapter {
    FailureAlternativeRoutesDto adapte(FailureAlternativeRoute failureAlternativeRoute);
}
