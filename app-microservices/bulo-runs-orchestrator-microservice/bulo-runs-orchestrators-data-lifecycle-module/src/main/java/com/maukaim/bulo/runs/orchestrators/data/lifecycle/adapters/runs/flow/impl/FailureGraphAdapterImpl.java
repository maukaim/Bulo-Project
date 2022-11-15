package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FailureGraphAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FailedRunRoute;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FailureGraph;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FailedRunAlternativeRouteDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FailureGraphDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FailureGraphAdapterImpl implements FailureGraphAdapter {
    @Override
    public FailureGraph adapte(FailureGraphDto dto) {
        Set<FailedRunAlternativeRouteDto> failedRunAlternativeRoutes = dto.getFailedRunAlternativeRoutes();
        Map<ContextStageId, FailedRunRoute> map = failedRunAlternativeRoutes == null ? Map.of() : failedRunAlternativeRoutes.stream()
                .collect(Collectors.toMap(
                        frar -> frar.getOrigin(),
                        frar -> new FailedRunRoute(frar.getDestination(), frar.getRemainingUsage())
                ));
        return new FailureGraph(map);
    }
}
