package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FailureGraphDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FailureGraph;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FailedRunAlternativeRouteDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FailureGraphDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FailureGraphDtoAdapterImpl implements FailureGraphDtoAdapter {
    @Override
    public FailureGraphDto adapte(FailureGraph failureGraph) {
        Set<FailedRunAlternativeRouteDto> failedRunAlternativeRouteDtos = failureGraph.asMap().entrySet().stream()
                .map(entry -> new FailedRunAlternativeRouteDto(
                        entry.getKey(),
                        entry.getValue().getDestination(),
                        entry.getValue().getRemainingUsage()
                ))
                .collect(Collectors.toSet());
        return new FailureGraphDto(failedRunAlternativeRouteDtos);
    }
}
