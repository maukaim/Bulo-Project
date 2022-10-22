package com.maukaim.bulo.flows.core.util;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowUtils {

    public static Set<ContextualizedStageId> getAllFlowStageIds(Flow flow) {
        if (flow == null || flow.getFlowStages() == null) {
            return Set.of();
        }

        return flow.getFlowStages().stream()
                .map(FlowStage::getFlowStageId)
                .collect(Collectors.toSet());
    }

    public static Set<String> getInputIds(Set<IoDependency> ioDependencies) {
        return ioDependencies == null ? Set.of() : ioDependencies.stream()
                .map(IoDependency::getInputId)
                .collect(Collectors.toSet());
    }
}
