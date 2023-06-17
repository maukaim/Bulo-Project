package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.core.FlowValidationException;
import com.maukaim.bulo.flows.core.FlowValidator;
import com.maukaim.bulo.flows.data.FlowStore;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FlowServiceImplTest {
    private FlowStore flowStore;
    private FlowValidator flowValidator;
    private FlowService flowService;

    @BeforeEach
    void init() {
        flowStore = mock(FlowStore.class);
        flowValidator = mock(FlowValidator.class);
        flowService = new FlowServiceImpl(flowStore, flowValidator);
    }

    @Test
    public void getFlowPassThrough() {
        String flowId = "test";
        Flow flow = mock(Flow.class);
        when(flowStore.getById(flowId)).thenReturn(flow);

        Flow result = flowService.getFlow(flowId);

        verify(flowStore).getById(flowId);
        assertSame(flow, result);
    }

    @Test
    public void getAllPassThrough() {
        Flow flow = mock(Flow.class);
        when(flowStore.getAll()).thenReturn(List.of(flow));

        List<Flow> result = flowService.getAll();

        verify(flowStore).getAll();
        assertEquals(List.of(flow), result);
    }

    @Test
    public void archive_whenFlowNotFound_thenReturnNull(){
        String flowId = "flowId";
        when(flowStore.getById(flowId)).thenReturn(null);

        Flow result = flowService.archive(flowId);

        verify(flowStore).getById(flowId);
        verify(flowStore, never()).remove(any());
        assertNull(result);
    }

    @Test
    public void archive_whenFlowFound_thenRemoveFromStore(){
        String flowId = "flowId";
        Flow flow = mock(Flow.class);
        when(flowStore.getById(flowId)).thenReturn(flow);
        when(flowStore.remove(flow)).thenReturn(flow);

        Flow result = flowService.archive(flowId);

        verify(flowStore).getById(flowId);
        verify(flowStore).remove(flow);
        assertSame(flow, result);
    }

    @Test
    public void create_whenFlowValidatorDoesNotThrow_thenAddIdAndPutInStore() throws FlowValidationException {
        Flow flow = mock(Flow.class);
        OwnerKey ownerKey = mock(OwnerKey.class);
        FlowStage flowStage = mock(FlowStage.class);

        when(flow.getFlowId()).thenReturn(null);
        when(flow.isParallelRunAllowed()).thenReturn(true);
        when(flow.getOwnerKeys()).thenReturn(Set.of(ownerKey));
        when(flow.getFlowStages()).thenReturn(Set.of(flowStage));

        String flowIdCreated = flowService.create(flow);

        verify(flowValidator).validate(flow);
        verify(flowStore).put(argThat((f) -> f.getFlowId() != null
                && f.isParallelRunAllowed()
                && f.getFlowStages().contains(flowStage)
                && f.getOwnerKeys().contains(ownerKey)));

        assertNotNull(flowIdCreated);
    }

    @Test
    public void create_whenFlowHadPreShotIdAndFlowValidatorThrows_thenNotAddedToStoreAndThrowRuntimeException() throws FlowValidationException {
        when(flowValidator.validate(any())).thenThrow(new FlowValidationException("test"));
        Flow flow = mock(Flow.class);
        when(flow.getFlowId()).thenReturn("myId");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> flowService.create(flow));

        assertEquals("Flow validation failed, won't register flow Id myId", exception.getMessage());
        verify(flowStore, never()).put(any());
    }

    @Test
    public void create_whenFlowDoesNotHaveIdYetAndFlowValidatorThrows_thenNotAddedToStoreAndThrowRuntimeException() throws FlowValidationException {
        when(flowValidator.validate(any())).thenThrow(new FlowValidationException("test"));
        Flow flow = mock(Flow.class);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> flowService.create(flow));

        assertEquals("Flow validation failed, won't register flow Id null", exception.getMessage());
        verify(flowStore, never()).put(any());
    }

}