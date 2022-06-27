package com.maukaim.boule.flows.orchestrator.flow;

import com.maukaim.boule.flows.orchestrator.flow.model.Flow;
import com.maukaim.boule.flows.orchestrator.flow.exceptions.FlowStartException;
import com.maukaim.boule.flows.orchestrator.run.FlowRun;
import com.maukaim.boule.flows.orchestrator.run.FlowRunService;
import com.maukaim.boule.triggers.api.TriggerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//TODO: Should be a dependency of FlowRun ! Its only purpose is to manage access to FlowCache, and maybe to manage update of it?
@Service
public class FlowServiceImpl implements FlowService {

    private final FlowCache flowCache;

    @Autowired
    public FlowServiceImpl(FlowCache flowCache) {
        this.flowCache = flowCache;
    }

    @Override
    public Optional<Flow> getFlow(String flowId) {
        return this.flowCache.getById(flowId);
    }
}
