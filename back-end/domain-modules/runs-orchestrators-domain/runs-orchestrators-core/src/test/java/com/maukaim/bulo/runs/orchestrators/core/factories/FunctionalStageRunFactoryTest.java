package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

public class FunctionalStageRunFactoryTest {

    private FunctionalStageRunFactory functionalStageRunFactory;
    private FunctionalStageRun mockFunctionalStageRun;
    private FunctionalStageDefinition mockFunctionalStageDefinition;
    private RunContext mockRunContext;
    private ContextStageId mockContextStageId;
    private RunDependency mockRunDependency;
    private FsStage fsStage;
    private ContextStageId fsStageContextId = mock(ContextStageId.class);
    private IoDependency fsStageIoDependency = mock(IoDependency.class);
    private InputProvider fsStageInputProvider = mock(InputProvider.class);

    private final String inputId = "inputId";


    @BeforeEach
    public void setup() {
        functionalStageRunFactory = new FunctionalStageRunFactory();
        mockFunctionalStageRun = mock(FunctionalStageRun.class);
        mockFunctionalStageDefinition = mock(FunctionalStageDefinition.class);
        mockRunContext = mock(RunContext.class);
        mockContextStageId = mock(ContextStageId.class);
        mockRunDependency = mock(RunDependency.class);
        fsStage = mock(FsStage.class);
    }

    @Test
    public void updateState_whenNewStatusProvided_statusUpdated() {
        OrchestrableContextStatus newStatus = OrchestrableContextStatus.PAUSED;
        FunctionalStageRun updatedStageRun = functionalStageRunFactory.updateState(mockFunctionalStageRun, newStatus);
        assertEquals(newStatus, updatedStageRun.getStatus());
    }

    @Test
    public void updateStageRunView_whenNewViewProvided_viewUpdated() {
        Map<String, StageRun<?>> mapOfViewToBeUpdated = new HashMap<>();
        mapOfViewToBeUpdated.put("testKey", mock(StageRun.class));

        when(mockFunctionalStageRun.getStageRunsById()).thenReturn(new HashMap<>());

        FunctionalStageRun updatedStageRun = functionalStageRunFactory.updateStageRunView(mockFunctionalStageRun, mapOfViewToBeUpdated);
        assertEquals(mapOfViewToBeUpdated, updatedStageRun.getStageRunsById());
    }

    @Test
    public void create_whenValidParamsProvided_functionalStageRunCreated() {
        Set<RunDependency> runDependencies = Set.of(mockRunDependency);
        when(mockFunctionalStageDefinition.getOutputProviders()).thenReturn(new HashSet<>());
        when(mockFunctionalStageDefinition.getFunctionalSubStages()).thenReturn(Set.of(fsStage));
        when(fsStage.getContextualizedId()).thenReturn(fsStageContextId);
        when(fsStage.getIoDependencies()).thenReturn(Set.of(fsStageIoDependency));
        when(fsStageIoDependency.getInputId()).thenReturn(inputId);
        when(fsStageIoDependency.getInputProviders()).thenReturn(Set.of(fsStageInputProvider));

        FunctionalStageRun newStageRun = functionalStageRunFactory.create(mockFunctionalStageDefinition, mockRunContext, mockContextStageId, runDependencies);
        assertEquals(mockRunContext, newStageRun.getContext());
        assertEquals(runDependencies, newStageRun.getStageRunDependencies());
        assertNotNull(newStageRun.getContextId());
        assertEquals(OrchestrableContextStatus.NEW, newStageRun.getStatus());
    }
}
