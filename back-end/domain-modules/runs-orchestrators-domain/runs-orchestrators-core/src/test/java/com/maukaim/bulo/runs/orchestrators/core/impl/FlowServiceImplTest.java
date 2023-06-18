package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlowServiceImplTest {

    private FlowStore flowStore;
    private FlowService flowService;

    @BeforeEach
    void setup() {
        this.flowStore = mock(FlowStore.class);
        this.flowService = new FlowServiceImpl(flowStore);
    }

    @Test
    public void getFlow_whenFlowIdExists_thenReturnFlow() {
        Flow expectedFlow = mock(Flow.class); // Create a Flow instance here.
        String flowId = "some-flow-id"; // Provide a valid flow id.

        when(flowStore.getById(flowId)).thenReturn(Optional.of(expectedFlow));

        Optional<Flow> actualFlow = flowService.getFlow(flowId);

        assertTrue(actualFlow.isPresent());
        assertEquals(expectedFlow, actualFlow.get());
        verify(flowStore, times(1)).getById(flowId);
    }

    @Test
    public void getFlow_whenFlowIdDoesNotExist_thenReturnEmpty() {
        String nonExistentFlowId = "non-existent-flow-id";

        when(flowStore.getById(nonExistentFlowId)).thenReturn(Optional.empty());

        Optional<Flow> actualFlow = flowService.getFlow(nonExistentFlowId);

        assertTrue(actualFlow.isEmpty());
        verify(flowStore, times(1)).getById(nonExistentFlowId);
    }
}
