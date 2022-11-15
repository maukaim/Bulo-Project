package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import java.util.Set;

public class FailureGraphDto {
    private final Set<FailedRunAlternativeRouteDto> failedRunAlternativeRoutes;

    public FailureGraphDto(Set<FailedRunAlternativeRouteDto> failedRunAlternativeRoutes) {
        this.failedRunAlternativeRoutes = failedRunAlternativeRoutes;
    }

    public Set<FailedRunAlternativeRouteDto> getFailedRunAlternativeRoutes() {
        return failedRunAlternativeRoutes;
    }

    @Override
    public String toString() {
        return "FailureGraphDto{" +
                "failedRunAlternativeRoutes=" + failedRunAlternativeRoutes +
                '}';
    }
}
