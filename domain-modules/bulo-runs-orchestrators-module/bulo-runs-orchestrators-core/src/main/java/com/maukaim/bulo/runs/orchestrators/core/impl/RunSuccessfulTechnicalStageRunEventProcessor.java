package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.TechnicalStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.utils.OrchestrableUtils;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class RunSuccessfulTechnicalStageRunEventProcessor extends TechnicalStageRunEventProcessor {

    public RunSuccessfulTechnicalStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService, stageRunService);
    }

    public void process(String stageRunId, Instant instant, FunctionalStageRunContext context) {
        System.out.println("Successful Run event... " + stageRunId);
        stageRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun) -> commonProcess(actualFunctionalStageRun, stageRunId, instant));
    }

    public void process(String stageRunId, Instant instant, FlowRunContext context) {
        System.out.println("Successful Run event... " + stageRunId);
        flowRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFlowRun) -> commonProcess(actualFlowRun, stageRunId, instant));
    }


    private Map<String, StageRun> commonProcess(OrchestrableRunContext<?> orchestrableRunContext, String stageRunId, Instant instant) {
        StageRun<?> actualRun = getActualRun(orchestrableRunContext, stageRunId);
        Map<String, StageRun> result = new HashMap<>();
        Map<String, StageRun> currentRunResult = splitProcess(actualRun,
                functionalStageRun -> Map.of(stageRunId, FunctionalStageRunFactory.updateState(functionalStageRun, OrchestrableContextStatus.SUCCESS)),
                technicalStageRun -> Map.of(stageRunId, TechnicalStageRunFactory.success(technicalStageRun, instant))
        );
        result.putAll(currentRunResult);

        Set<ContextStageId> childrenIds = orchestrableRunContext.getExecutionGraph().getChildren(actualRun.getContextualizedStageId());
        if (!orchestrableRunContext.getStatus().isTerminal() && !childrenIds.isEmpty()) {
            result.putAll(this.getStageRunsToBeRequestedIfAuthorized(orchestrableRunContext, actualRun.getContextualizedStageId(), childrenIds));
        }

        return result;
    }


    private Map<String, StageRun> getStageRunsToBeRequestedIfAuthorized(OrchestrableRunContext<?> orchestrableRunContext, ContextStageId currentStageId, Set<ContextStageId> children) {
        Set<StageRun> previousStageRuns = orchestrableRunContext.getStageRunIds().stream()
                .map(this.stageRunService::getById)
                .collect(Collectors.toSet());
        Map<ContextStageId, Set<RunDependency>> childrenToStart = children.stream()
                .filter(childStageId -> orchestrableRunContext.otherAncestorsAreSuccessful(childStageId, currentStageId))
                .collect(toMap(
                        flowStageId -> flowStageId,
                        flowStageId -> OrchestrableUtils.getRunDependencies(flowStageId, orchestrableRunContext, previousStageRuns)
                ));
        return this.stageRunService.getNextStageRuns(orchestrableRunContext.toRunContext(), childrenToStart);
    }

}
