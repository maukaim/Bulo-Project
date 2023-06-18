package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public abstract class StageRunEventProcessorTest<T extends StageRunEventProcessor> {
    protected T stageRunEventProcessor;
    protected FlowRunService flowRunService;
    protected StageRunService stageRunService;
    protected FunctionalStageRunFactory functionalStageRunFactory;
    protected TechnicalStageRunFactory technicalStageRunFactory;

    protected String stageRunId = "stageRunId";
    protected FunctionalStageRun stageRunAsFunctional = mock(FunctionalStageRun.class);
    protected TechnicalStageRun stageRunAsTechnical = mock(TechnicalStageRun.class);
    protected final String contextId = "contextId";
    protected final FlowRunContext flowRunContext = mock(FlowRunContext.class);
    protected final FunctionalStageRunContext functionalStageRunContext = mock(FunctionalStageRunContext.class);
    protected final FlowRun flowRun = mock(FlowRun.class);
    protected final FunctionalStageRun functionalStageRun = mock(FunctionalStageRun.class);

    protected ArgumentCaptor<Function<FlowRun, Map<String, StageRun<?>>>> frFunctionArgumentCaptor = ArgumentCaptor.forClass(Function.class);
    protected ArgumentCaptor<Function<FunctionalStageRun, Map<String, StageRun<?>>>> fsrFunctionArgumentCaptor = ArgumentCaptor.forClass(Function.class);

    @BeforeEach
    void abstractInit(){
        flowRunService = mock(FlowRunService.class);
        stageRunService = mock(StageRunService.class);
        technicalStageRunFactory = mock(TechnicalStageRunFactory.class);
        functionalStageRunFactory = mock(FunctionalStageRunFactory.class);
        stageRunEventProcessor = getStageRunEventProcessor();

        when(flowRunContext.getContextId()).thenReturn(contextId);
        when(functionalStageRunContext.getContextId()).thenReturn(contextId);
        when(stageRunAsFunctional.getStageRunId()).thenReturn(stageRunId);
        when(stageRunAsTechnical.getStageRunId()).thenReturn(stageRunId);
    }

    protected abstract T getStageRunEventProcessor();

    protected void getActualRunExceptionSetup(){
        when(stageRunService.getById(stageRunId)).thenReturn(null);
    }

    protected Function<FlowRun, Map<String, StageRun<?>>> getFunctionPassedToFlowRunService() {
        verify(flowRunService).computeStageRunUpdateUnderLock(eq(contextId), frFunctionArgumentCaptor.capture());
        return frFunctionArgumentCaptor.getValue();
    }

    protected Function<FunctionalStageRun, Map<String, StageRun<?>>> getFunctionPassedToStageRunService() {
        verify(stageRunService).computeStageRunUpdateUnderLock(eq(contextId), fsrFunctionArgumentCaptor.capture());
        return fsrFunctionArgumentCaptor.getValue();
    }

}