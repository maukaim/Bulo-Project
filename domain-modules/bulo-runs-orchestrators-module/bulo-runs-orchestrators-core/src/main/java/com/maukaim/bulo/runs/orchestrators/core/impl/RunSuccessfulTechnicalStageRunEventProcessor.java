package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.TechnicalStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.RunContextType;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class RunSuccessfulTechnicalStageRunEventProcessor extends TechnicalStageRunEventProcessor {

    public RunSuccessfulTechnicalStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService, stageRunService);
    }

    public void process(String stageRunId, Instant instant, FunctionalStageContext context) {
        System.out.println("Successful Run event... " + stageRunId);
        stageRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun)-> commonProcess(actualFunctionalStageRun, stageRunId, instant));
    }

    public void process(String stageRunId, Instant instant, FlowContext context) {
        System.out.println("Successful Run event... " + stageRunId);
        flowRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFlowRun) -> commonProcess(actualFlowRun, stageRunId, instant));
    }


    private Map<String, StageRun> commonProcess(OrchestrableContext<?> orchestrableContext, String stageRunId, Instant instant){
        TechnicalStageRun actualTechnicalStageRun = getActualTechnicalRun(orchestrableContext, stageRunId);
        if (actualTechnicalStageRun == null) {
            throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
        }

        Map<String, StageRun> result = new HashMap<>();
        result.put(stageRunId, TechnicalStageRunFactory.success(actualTechnicalStageRun, instant));

        Set<ContextualizedStageId> childrenIds = orchestrableContext.getExecutionGraph().getChildren(actualTechnicalStageRun.getContextualizedStageId());
        if (!orchestrableContext.getStatus().isTerminal() && !childrenIds.isEmpty()) {
            result.putAll(this.getStageRunsToBeRequestedIfAuthorized(orchestrableContext, actualTechnicalStageRun.getContextualizedStageId(), childrenIds));
        }

        return result;
    }


    private Map<String, StageRun> getStageRunsToBeRequestedIfAuthorized(OrchestrableContext<?> orchestrableContext, ContextualizedStageId currentStageId, Set<ContextualizedStageId> children) {
        Map<ContextualizedStageId, Set<StageRunDependency>> childrenToStart = children.stream()
                .filter(childStageId -> orchestrableContext.otherAncestorsAreSuccessful(childStageId, currentStageId))
                .collect(toMap(
                        flowStageId -> flowStageId,
                        flowStageId -> this.getDependencies(flowStageId, orchestrableContext)
                ));
        return this.stageRunService.getNextStageRuns(resolveContext(orchestrableContext.getContextType(), orchestrableContext.getContextId()), childrenToStart);
    }

    private Context<?> resolveContext(RunContextType runContextType, Object contextId) {
        return switch (runContextType){
            case FLOW_RUN -> new FlowContext((String)contextId);
            case FUNCTIONAL_STAGE_RUN -> new FunctionalStageContext((String)contextId);
        };
    }

    //TODO: tout ca devrait sortir de la class non?
    private Set<StageRunDependency> getDependencies(ContextualizedStageId contextualizedStageId, OrchestrableContext<?> orchestrableContext) {
        Set<StageRunDependency> result = new HashSet<>();

        Set<ContextualizedStageDependency> flowStageDependencies = orchestrableContext.getExecutionGraph().getFlowStageDependencies(contextualizedStageId);
        for (ContextualizedStageDependency contextualizedStageDependency : flowStageDependencies) {
            String inputId = contextualizedStageDependency.getInputId();
            Set<FlowStageAncestor> flowStageAncestors = contextualizedStageDependency.getAncestors();
            Set<StageRunAncestor> stageRunAncestors = getStageRunAncestors(flowStageAncestors, orchestrableContext);
            result.add(new StageRunDependency(inputId, stageRunAncestors));
        }

        return result;
    }

    //TODO: tout ca devrait sortir de la class non?
    private Set<StageRunAncestor> getStageRunAncestors(Set<FlowStageAncestor> flowStageAncestors, OrchestrableContext<?> orchestrableContext) {
        if (flowStageAncestors == null || flowStageAncestors.isEmpty()) {
            return Set.of();
        }

        Map<ContextualizedStageId, String> alreadyRanFlowStageIdByStageRunIds = orchestrableContext.getStageRunIds().stream()
                .map(this.stageRunService::getById)
                .filter(Objects::nonNull)
                .collect(toMap(
                        stageRun -> stageRun.getContextualizedStageId(),
                        stageRun -> stageRun.getStageRunId()
                ));
        return flowStageAncestors.stream()
                .map(flowStageAncestor -> new StageRunAncestor(
                        alreadyRanFlowStageIdByStageRunIds.get(flowStageAncestor.getFlowStageId()),
                        flowStageAncestor.getOutputIds()))
                .collect(Collectors.toSet());
    }

}
