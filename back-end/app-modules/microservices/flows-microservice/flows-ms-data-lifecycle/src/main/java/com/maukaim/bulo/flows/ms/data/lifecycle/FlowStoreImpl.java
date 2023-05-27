package com.maukaim.bulo.flows.ms.data.lifecycle;

import com.maukaim.bulo.flows.data.FlowStore;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.io.flows.system.FlowEvent;
import com.maukaim.bulo.io.flows.client.model.FlowDto;
import com.maukaim.bulo.io.flows.system.FlowEventType;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowStoreImpl implements FlowStore {
    private final Map<String, Flow> flowById;
    private final FlowEventPublisher flowEventPublisher;
    private final FlowDtoAdapter flowDtoAdapter;

    public FlowStoreImpl(Map<String, Flow> initialCache,
                         FlowEventPublisher flowEventPublisher,
                         FlowDtoAdapter flowDtoAdapter) {
        this.flowById = new HashMap<>(initialCache);
        this.flowEventPublisher = flowEventPublisher;
        this.flowDtoAdapter = flowDtoAdapter;
    }

    @Override
    public Flow getById(String flowId) {
        return this.flowById.get(flowId);
    }

    @Override
    public Flow put(Flow flow) {
        FlowDto dto = this.flowDtoAdapter.adapte(flow);
        dto.setFlowId(flow.getFlowId());
        return this.publishUpdateOrSave(flow, dto);
    }

    private Flow publishUpdateOrSave(Flow flow, FlowDto dto) {
        FlowEvent event = new FlowEvent(dto, FlowEventType.UPDATED, Instant.now());
        boolean published = this.flowEventPublisher.publish(event);
        return published ? flow : this.save(flow);
    }

    @Override
    public Flow remove(Flow flow) {
        FlowDto dto = this.flowDtoAdapter.adapte(flow);
        FlowEvent event = new FlowEvent(dto, FlowEventType.ARCHIVED, Instant.now());
        boolean published = this.flowEventPublisher.publish(event);
        return published ? flow : this.delete(flow.getFlowId());
    }

    @Override
    public List<Flow> getAll() {
        return flowById.values().stream().toList();
    }

    public Flow save(Flow flow) {
        System.out.println("Saving flow: " + flow);
        this.flowById.put(flow.getFlowId(), flow);
        return flow;
    }

    public Flow delete(String flowId) {
        return this.flowById.remove(flowId);
    }
}
