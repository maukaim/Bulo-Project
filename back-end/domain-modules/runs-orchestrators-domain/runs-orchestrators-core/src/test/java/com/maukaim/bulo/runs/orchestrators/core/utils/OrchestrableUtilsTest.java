package com.maukaim.bulo.runs.orchestrators.core.utils;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrchestrableUtilsTest {
    private final ContextStageId contextStageId1 = mock(ContextStageId.class);
    private final ContextStageId contextStageId2 = mock(ContextStageId.class);
    private final String inputId1 = "inputId1";
    private final String inputId2 = "inputId2";
    private final String stageRunId1 = "stageRunId";
    private final String stageRunId2 = "stageRunId2";
    private final String subStageRunId1 = "subStageRunId1";
    private final ContextStageId subContextStageId1 = mock(ContextStageId.class);
    private final ContextStageId subContextStageId2 = mock(ContextStageId.class);
    private final OutputProvider outputProvider1 = mock(OutputProvider.class);
    private final OrchestrableRunContext orchestrableRunContext = mock(OrchestrableRunContext.class);
    private final TechnicalStageRun stageRun1 = mock(TechnicalStageRun.class);
    private final FunctionalStageRun stageRun2 = mock(FunctionalStageRun.class);
    private final TechnicalStageRun subStageRun1 = mock(TechnicalStageRun.class);
    private final TechnicalStageRun subStageRun2 = mock(TechnicalStageRun.class);


    private final ContextualizedStageDependency contextualizedStageDependency1 = mock(ContextualizedStageDependency.class);
    private final ContextualizedStageDependency contextualizedStageDependency2 = mock(ContextualizedStageDependency.class);

    private final ContextStageAncestor commonAncestor = mock(ContextStageAncestor.class);

    private final ExecutionGraph executionGraph = mock(ExecutionGraph.class);

    private OrchestrableUtils orchestrableUtils;
    //Pour chaque Dependent Stage, regarder si y a des ancestors qui ont
    @BeforeEach
    void init() {
        when(stageRun1.getStageRunId()).thenReturn(stageRunId1);
        when(stageRun2.getStageRunId()).thenReturn(stageRunId2);

        when(orchestrableRunContext.getExecutionGraph()).thenReturn(executionGraph);
        when(executionGraph.getFlowStageDependencies(contextStageId1)).thenReturn(
                Set.of(contextualizedStageDependency1, contextualizedStageDependency2)
        );

        when(stageRun1.getContextualizedStageId()).thenReturn(contextStageId1);
        when(stageRun2.getContextualizedStageId()).thenReturn(contextStageId2);

        when(commonAncestor.getContextStageId()).thenReturn(contextStageId1);
        when(commonAncestor.getOutputIds()).thenReturn(Set.of(inputId1));
        when(contextualizedStageDependency1.getAncestors()).thenReturn(Set.of(commonAncestor));
        when(contextualizedStageDependency1.getInputId()).thenReturn(inputId1);
        when(contextualizedStageDependency2.getInputId()).thenReturn(inputId2);
        when(contextualizedStageDependency2.getAncestors()).thenReturn(Set.of(commonAncestor));

        when(stageRun2.getAllStageRuns()).thenReturn(Set.of(subStageRun1, subStageRun2));
        when(subStageRun1.getContextualizedStageId()).thenReturn(subContextStageId1);
        when(subStageRun2.getContextualizedStageId()).thenReturn(subContextStageId2);
        when(stageRun2.getOutputProviders()).thenReturn(Set.of(outputProvider1));
        when(outputProvider1.getContextStageId()).thenReturn(subContextStageId1);
        when(outputProvider1.getOutputIds()).thenReturn(Set.of(inputId1));
        when(subStageRun1.getStageRunId()).thenReturn(subStageRunId1);

        orchestrableUtils = new OrchestrableUtils();
    }

    @Test
    void getRunDependency_whenAncestorIsTechnical(){
        Set<RunDependency> runDependencies = orchestrableUtils.getRunDependencies(contextStageId1, orchestrableRunContext, Set.of(stageRun1, stageRun2));

        Assertions.assertEquals(2, runDependencies.size());
        runDependencies.forEach((dep)->{
            Assertions.assertEquals(1, dep.getAncestors().size());
            Assertions.assertEquals(stageRunId1, dep.getAncestors().iterator().next().getStageRunId());
        });
    }

    @Test
    void getRunDependency_whenNoAncestors(){
        when(contextualizedStageDependency1.getAncestors()).thenReturn(Set.of());
        when(contextualizedStageDependency2.getAncestors()).thenReturn(Set.of());

        Set<RunDependency> runDependencies = orchestrableUtils.getRunDependencies(contextStageId1, orchestrableRunContext, Set.of(stageRun1, stageRun2));

        Assertions.assertEquals(2, runDependencies.size());
        runDependencies.forEach((dep)->{
            Assertions.assertEquals(0, dep.getAncestors().size());
        });
    }

    @Test
    void getRunDependency_whenAncestorIsFunctional_ancestorIsTheTechnicalStageRunInsideTheFunctionalRun(){
        when(commonAncestor.getContextStageId()).thenReturn(contextStageId2);

        Set<RunDependency> runDependencies = orchestrableUtils.getRunDependencies(contextStageId1, orchestrableRunContext, Set.of(stageRun1, stageRun2));

        Assertions.assertEquals(2, runDependencies.size());
        runDependencies.forEach((dep)->{
            Assertions.assertEquals(1, dep.getAncestors().size());
            Assertions.assertEquals(subStageRunId1, dep.getAncestors().iterator().next().getStageRunId());
        });
    }

    @Test
    void getRunDependency_whenAncestorIsFunctional_andItsRunMapDoesNotContainTheOutputProvider_thenThrow(){
        when(commonAncestor.getContextStageId()).thenReturn(contextStageId2);
        ContextStageId outputProviderContextStageId = mock(ContextStageId.class);
        when(outputProvider1.getContextStageId()).thenReturn(outputProviderContextStageId);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> orchestrableUtils.getRunDependencies(contextStageId1, orchestrableRunContext, Set.of(stageRun1, stageRun2)));

        Assertions.assertEquals("No Run available for contextStageId "+ outputProviderContextStageId, exception.getMessage());
    }

    @Test
    void hehe(){
        StageRun stageRun = mock(StageRun.class);
        when(stageRun.getContextualizedStageId()).thenReturn(contextStageId1);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> orchestrableUtils.getRunDependencies(contextStageId1, orchestrableRunContext, Set.of(stageRun, stageRun2)));

        Assertions.assertEquals("StageRun type not supported: "+ stageRun.getClass().getName(), exception.getMessage());
    }
}