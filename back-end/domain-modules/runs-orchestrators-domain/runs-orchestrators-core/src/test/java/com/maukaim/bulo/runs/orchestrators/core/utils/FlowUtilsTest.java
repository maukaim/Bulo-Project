package com.maukaim.bulo.runs.orchestrators.core.utils;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlowUtilsTest {

    private FlowUtils flowUtils;

    @BeforeEach
    public void setup() {
        flowUtils = new FlowUtils();
    }

    @Test
    public void getRootIds_whenFlowIsNull_emptySet() {
        Set<ContextStageId> result = flowUtils.getRootIds(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getRootIds_whenFlowStagesIsNull_emptySet() {
        Flow flow = mock(Flow.class);
        when(flow.getFlowStages()).thenReturn(null);

        Set<ContextStageId> result = flowUtils.getRootIds(flow);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getRootIds_whenIoDependenciesAreEmpty_returnSetWithStageIds() {
        Flow flow = mock(Flow.class);
        FlowStage flowStage = mock(FlowStage.class);
        ContextStageId contextStageId = mock(ContextStageId.class);
        when(flow.getFlowStages()).thenReturn(Set.of(flowStage));
        when(flowStage.getIoDependencies()).thenReturn(Set.of());
        when(flowStage.getFlowStageId()).thenReturn(contextStageId);

        Set<ContextStageId> result = flowUtils.getRootIds(flow);
        assertEquals(1, result.size());
        assertTrue(result.contains(contextStageId));
    }

    @Test
    public void getRootIds_whenIoDependenciesAreNotEmpty_emptySet() {
        Flow flow = mock(Flow.class);
        FlowStage flowStage = mock(FlowStage.class);
        InputDependency inputDependency = mock(InputDependency.class);
        when(flow.getFlowStages()).thenReturn(Set.of(flowStage));
        when(flowStage.getIoDependencies()).thenReturn(Set.of(inputDependency));

        Set<ContextStageId> result = flowUtils.getRootIds(flow);
        assertTrue(result.isEmpty());
    }


}