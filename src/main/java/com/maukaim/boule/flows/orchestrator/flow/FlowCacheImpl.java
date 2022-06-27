package com.maukaim.boule.flows.orchestrator.flow;

import com.maukaim.boule.flows.orchestrator.flow.model.Flow;
import com.maukaim.boule.flows.orchestrator.flow.FlowCache;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.maukaim.boule.flows.orchestrator.factory.FakeContextProvider.FLOW_1;

@Service
public class FlowCacheImpl implements FlowCache {

    private final ConcurrentHashMap<String, Flow> cache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        this.cache.put(FLOW_1.getFlowId(), FLOW_1);
    }

    @Override
    public Collection<Flow> getAll() {
        return Set.copyOf(this.cache.values());
    }

    @Override
    public Optional<Flow> getById(String flowId) {
        return Optional.ofNullable(this.cache.get(flowId));
    }
}
