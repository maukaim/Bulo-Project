package com.maukaim.bulo.flows.core;

public interface FlowCache {
    Flow addOrUpdate(Flow flow);
    Flow remove(String flowId);
    Flow get(String flowId);
}
