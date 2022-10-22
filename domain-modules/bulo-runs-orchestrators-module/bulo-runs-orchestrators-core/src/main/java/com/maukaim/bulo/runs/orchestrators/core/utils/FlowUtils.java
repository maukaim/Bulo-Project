package com.maukaim.bulo.runs.orchestrators.core.utils;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowUtils {

    public static Set<ContextualizedStageId> getRootIds(Flow flow){
        if(flow == null || flow.getFlowStages() == null){
            return Set.of();
        }else{
            return flow.getFlowStages().stream()
                    .filter(flowStage -> {
                        Set<InputDependency> ioDependencies = flowStage.getIoDependencies();
                        return ioDependencies == null || ioDependencies.isEmpty();
                    })
                    .map(FlowStage::getFlowStageId)
                    .collect(Collectors.toSet());
        }
    }
}
