package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlowRunFactoryTest {
    private FlowRunFactory factory;
    private InputDependency inputDependency = mock(InputDependency.class);
    private InputProvider inputProvider = mock(InputProvider.class);
    private Flow flow = mock(Flow.class);
    private FlowStage flowStage = mock(FlowStage.class);


    @BeforeEach
    public void setup() {
        this.factory = new FlowRunFactory();
        when(flow.getFlowStages()).thenReturn(Set.of(flowStage));
        when(flowStage.getIoDependencies()).thenReturn(Set.of(inputDependency));
        when(flowStage.getFlowStageId()).thenReturn(ContextStageId.of("stage1"));
        when(inputDependency.getInputProviders()).thenReturn(Set.of(inputProvider));
        when(inputDependency.getInputId()).thenReturn("inputId");
        when(inputProvider.getFlowStageId()).thenReturn(ContextStageId.of("stage2"));
        when(inputProvider.getOutputIds()).thenReturn(Set.of("outputId"));

    }

    @Test
    public void updateState_whenFlowRunAndNewStatus_providesUpdatedFlowRun() {
        // Arrange
        FlowRun flowRun = mock(FlowRun.class);
        OrchestrableContextStatus status = OrchestrableContextStatus.NEW;

        // Act
        FlowRun updatedFlowRun = factory.updateState(flowRun, status);

        // Assert
        assertEquals(status, updatedFlowRun.getStatus());
    }

    @Test
    public void updateStageRunView_whenFlowRunAndUpdatedView_providesUpdatedFlowRun() {
        // Arrange
        FlowRun flowRun = mock(FlowRun.class);
        Map<String, StageRun<?>> viewMap = new HashMap<>();
        StageRun<?> stageRun = mock(StageRun.class);
        viewMap.put("stage1", stageRun);

        // Act
        FlowRun updatedFlowRun = factory.updateStageRunView(flowRun, viewMap);

        // Assert
        assertNotSame(flowRun.getStageRunsById(), updatedFlowRun.getStageRunsById());
        assertEquals(viewMap, updatedFlowRun.getStageRunsById());
    }

    @Test
    public void create_whenFlow_providesNewFlowRunWithNewStatus() {

        // Act
        FlowRun createdFlowRun = factory.create(flow);

        // Assert
        assertEquals(flow.getFlowId(), createdFlowRun.getFlowId());
        assertEquals(OrchestrableContextStatus.NEW, createdFlowRun.getStatus());
    }
}
