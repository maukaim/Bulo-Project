package com.maukaim.bulo.runs.orchestrators.core.utils;

import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrchestrableContextStatusResolverTest {
    private OrchestrableContextStatusResolver orchestrableContextStatusResolver;
    private OrchestrableRunContext orchestrableRunContext = mock(OrchestrableRunContext.class);
    private final TechnicalStageRun stageRun1 = mock(TechnicalStageRun.class);
    private final FunctionalStageRun stageRun2 = mock(FunctionalStageRun.class);
    private final String stageRun1Id = "stageRun1Id";
    private final String stageRun2Id = "stageRun2Id";
    @BeforeEach
    void init(){
        orchestrableContextStatusResolver = new OrchestrableContextStatusResolver();
        when(orchestrableRunContext.getStageRunsById()).thenReturn(Map.of(
                stageRun1Id, stageRun1,
                stageRun2Id, stageRun2
        ));

        when(stageRun1.getStageRunId()).thenReturn(stageRun1Id);
        when(stageRun2.getStageRunId()).thenReturn(stageRun2Id);
        when(stageRun1.getStageType()).thenReturn(StageType.TECHNICAL);
        when(stageRun2.getStageType()).thenReturn(StageType.FUNCTIONAL);

        when(orchestrableRunContext.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);

        when(orchestrableRunContext.allRunsAreTerminated()).thenReturn(false);
    }

    @Test
    void resolveStatus_WhenContextStatusIsCancel_thenReturnCancel(){
        when(orchestrableRunContext.getStatus()).thenReturn(OrchestrableContextStatus.CANCELLED);
        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.CANCELLED, statusResult);
    }

    @Test
    void resolveStatus_whenBothAreRunning_thenReturnRunning(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.RUNNING);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.RUNNING, statusResult);
    }

    @Test
    void resolveStatus_whenBothAreCancelled_thenReturnCancelled(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.CANCELLED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.CANCELLED);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.CANCELLED, statusResult);
    }

    @Test
    void resolveStatus_whenBothAreFailed_thenReturnFailed(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.FAILED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.FAILED, statusResult);
    }

    @Test
    void resolveStatus_whenBothAreInProcessToStart_andContextWasInNew_thenReturnPendingStart(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.ACKNOWLEDGED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.PENDING_START);
        when(orchestrableRunContext.getStatus()).thenReturn(OrchestrableContextStatus.NEW);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.PENDING_START, statusResult);
    }

    @Test
    void resolveStatus_whenAllRunsAreTerminated_thenReturnSuccess(){
        when(orchestrableRunContext.allRunsAreTerminated()).thenReturn(true);
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.SUCCESS);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.SUCCESS);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.SUCCESS, statusResult);
    }

    @Test
    void resolveStatus_whenOnlyOneIsSuccessful_thenReturnRunning(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.SUCCESS);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.RUNNING, statusResult);
    }

    @Test
    void resolveStatus_whenAllAreNew_ContextIsNew_thenReturnNew(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.TO_BE_REQUESTED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.NEW);
        when(orchestrableRunContext.getStatus()).thenReturn(OrchestrableContextStatus.NEW);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.NEW, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsCancelled_otherIsSuccess_thenReturnCancel(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.CANCELLED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.SUCCESS);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.CANCELLED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsCancelled_otherIsRunning_thenReturnCancel(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.CANCELLED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.CANCELLED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsCancelled_otherIsStarting_thenReturnCancel(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.CANCELLED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.PENDING_START);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.CANCELLED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsCancelled_otherIsNew_thenReturnCancel(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.CANCELLED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.NEW);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.CANCELLED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsFailed_otherIsCancelled_thenReturnFailed(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.FAILED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.CANCELLED);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.FAILED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsFailed_otherIsRunning_thenReturnFailed(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.FAILED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.FAILED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsFailed_otherIsSuccess_thenReturnFailed(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.FAILED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.SUCCESS);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.FAILED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsFailed_otherIsStarting_thenReturnFailed(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.FAILED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.PENDING_START);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.FAILED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsFailed_otherIsNew_thenReturnFailed(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.FAILED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.NEW);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.FAILED, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsSuccess_otherIsRunning_thenReturnRunning(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.SUCCESS);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.RUNNING, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsPendingStart_otherIsToBeRequested_thenReturnPendingStart(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.TO_BE_REQUESTED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.PENDING_START);
        when(orchestrableRunContext.getStatus()).thenReturn(OrchestrableContextStatus.NEW);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.PENDING_START, statusResult);
    }

    @Test
    void resolveStatus_whenOneIsPendingStart_otherIsRequested_thenReturnPendingStart(){
        when(stageRun1.getStatus()).thenReturn(TechnicalStageRunStatus.REQUESTED);
        when(stageRun2.getStatus()).thenReturn(OrchestrableContextStatus.PENDING_START);
        when(orchestrableRunContext.getStatus()).thenReturn(OrchestrableContextStatus.NEW);

        OrchestrableContextStatus statusResult = orchestrableContextStatusResolver.resolveStatus(orchestrableRunContext);

        assertEquals(OrchestrableContextStatus.PENDING_START, statusResult);
    }




}