package com.maukaim.boule.flows.orchestrator;

import com.maukaim.boule.flows.orchestrator.cache.FlowRunCache;
import com.maukaim.boule.triggers.api.TriggerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowServiceImpl implements FlowService {

    private final FlowRunCache runCache;

    @Autowired
    public FlowServiceImpl(FlowRunCache runCache) {
        this.runCache = runCache;
    }

    @Override
    public void startFlow(TriggerEvent event) {
        //1 - Creer un Run
        //TODO: Ok on recoit l'info qu'il faut start... donc on start !
        // In a un cache de FlowRund
    }
}
