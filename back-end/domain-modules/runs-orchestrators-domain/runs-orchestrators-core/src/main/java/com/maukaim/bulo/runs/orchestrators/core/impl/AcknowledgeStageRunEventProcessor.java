package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.Map;

public class AcknowledgeStageRunEventProcessor extends StageRunEventProcessor {

    public AcknowledgeStageRunEventProcessor(FlowRunService flowRunService,
                                             StageRunService stageRunService,
                                             FunctionalStageRunFactory functionalStageRunFactory,
                                             TechnicalStageRunFactory technicalStageRunFactory) {
        super(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
    }

    public void process(String stageRunId, String executorId, FunctionalStageRunContext context) {
        System.out.println("Acknowledged Run event... " + stageRunId);
        stageRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun) -> commonProcess(actualFunctionalStageRun, stageRunId, executorId));
    }

    public void process(String stageRunId, String executorId, FlowRunContext context) {
        System.out.println("Acknowledged Run event... " + stageRunId);
        flowRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFlowRun) -> commonProcess(actualFlowRun, stageRunId, executorId));
    }

    private Map<String, StageRun<?>> commonProcess(OrchestrableRunContext<?> orchestrableRunContext, String stageRunId, String executorId) {
        StageRun<?> stageRun = getActualRun(stageRunId);
        if (orchestrableRunContext.getStatus().isProblem() && !stageRun.getStatus().isTerminal()) {
            this.stageRunService.requestCancel(stageRun.getStageRunId(), executorId);
        }
        return splitProcess(stageRun,
                functionalStageRun -> Map.of(stageRunId, functionalStageRunFactory.updateState(functionalStageRun, OrchestrableContextStatus.PENDING_START)),
                technicalStageRun -> Map.of(stageRunId, technicalStageRunFactory.acknowledged(technicalStageRun, executorId))
        );
    }
}
