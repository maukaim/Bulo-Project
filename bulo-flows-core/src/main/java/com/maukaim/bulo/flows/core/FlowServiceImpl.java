package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.io.FlowData;

public class FlowServiceImpl implements FlowService{
    private FlowCache flowCache;
    private FlowEventPublisher flowEventPublisher;

    @Override
    public Flow getFlow(String flowId) {
        return flowCache.get(flowId);
    }

    @Override
    public Flow create(FlowData flowData) {
        //1 - Verifier que l'Acyclic est respected
        //2 - Verifier que les Stage Filles peuvent bien parler aux Stages parent
        Flow newFlow = FlowBuilderImpl.build(flowData);
        return flowCache.addOrUpdate(newFlow);
    }

    @Override
    public Flow update(FlowData flowData) {
        Flow updatedFlow = FlowBuilderImpl.build(flowData);
        return flowCache.addOrUpdate(updatedFlow);
    }

    @Override
    public Flow archive(String flowId) {
        //1 - Verifier que aucun Run en cours? Ou osef?
        return flowCache.remove(flowId);
    }
}

/**
 * Le Runs Orchestrator doit avoir une vue du Flow qui lui dit dans quel etat est un Flow.
 * Si il est ARCHIVED, il ne doit pas commencer a run dessus.
 *
 */
