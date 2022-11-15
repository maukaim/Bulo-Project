package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class FailureGraph {
    private final Map<ContextStageId, FailedRunRoute> failedRunRoutesMap;

    public FailureGraph(Map<ContextStageId, FailedRunRoute> failedRoutes) {
        this.failedRunRoutesMap = new ConcurrentHashMap<>(failedRoutes);
    }

    public ContextStageId popAvailableSuccessor(ContextStageId key) {
        AtomicReference<ContextStageId> responseReference = new AtomicReference<>();
        failedRunRoutesMap.compute(key, (origin, failedRunRoute) -> {
            if (failedRunRoute == null) {
                return null;
            }
            if (failedRunRoute.getRemainingUsage() == 0) {
                return failedRunRoute;
            } else {
                responseReference.set(failedRunRoute.getDestination());
                return new FailedRunRoute(failedRunRoute.getDestination(),
                        failedRunRoute.getRemainingUsage() - 1);
            }
        });

        return responseReference.get();
    }

    public Map<ContextStageId, FailedRunRoute> asMap() {
        return Map.copyOf(failedRunRoutesMap);
    }
}
