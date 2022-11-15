package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.flow.*;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.*;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlowRunFactory {

    public static FlowRun updateState(FlowRun flowRun, OrchestrableContextStatus newStatus) {
        return new FlowRun(
                flowRun.getContextId(),
                flowRun.getFlowId(),
                flowRun.getExecutionGraph(),
                flowRun.getFailureGraph(),
                flowRun.getStageRunsById(),
                newStatus
        );
    }

    public static FlowRun updateStageRunView(FlowRun flowRun, Map<String, StageRun> mapOfViewToBeUpdated) {
        HashMap<String, StageRun> newStageRunViewMap = new HashMap<>(flowRun.getStageRunsById());
        newStageRunViewMap.putAll(mapOfViewToBeUpdated);
        return new FlowRun(flowRun.getContextId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), flowRun.getFailureGraph(), Map.copyOf(newStageRunViewMap), flowRun.getStatus());
    }

    public static FlowRun create(Flow flow) {
        return new FlowRun(UUID.randomUUID().toString(),
                flow.getFlowId(),
                new ExecutionGraph(resolveFlowStages(flow.getFlowStages())),
                new FailureGraph(resolveFailureAlternativeRoutes(flow.getFailureAlternativeRoutes())),
                new HashMap<>(),
                OrchestrableContextStatus.NEW);
    }

    private static Map<ContextStageId, FailedRunRoute> resolveFailureAlternativeRoutes(Set<FailureAlternativeRoute> failureAlternativeRoutes) {
        return failureAlternativeRoutes.stream()
                .collect(Collectors.toMap(
                        far -> far.getFrom(),
                        far -> new FailedRunRoute(far.getTo(), far.getMaxUsage())
                ));
    }

    private static Map<ContextStageId, Set<ContextualizedStageDependency>> resolveFlowStages(Set<FlowStage> flowStages) {
        return flowStages == null ? Map.of() : flowStages.stream()
                .collect(Collectors.toMap(
                        FlowStage::getFlowStageId,
                        flowStage -> resolveInputDependencies(flowStage.getIoDependencies())
                ));
    }

    private static Set<ContextualizedStageDependency> resolveInputDependencies(Set<InputDependency> inputDependencies) {
        return inputDependencies == null ? Set.of() : inputDependencies.stream()
                .map(FlowRunFactory::resolveInputDependency)
                .collect(Collectors.toSet());
    }

    private static ContextualizedStageDependency resolveInputDependency(InputDependency inputDependency) {
        return inputDependency == null ? null : new ContextualizedStageDependency(
                inputDependency.getInputId(),
                resolveInputProviders(inputDependency.getInputProviders())
        );
    }

    private static Set<ContextStageAncestor> resolveInputProviders(Set<InputProvider> inputProviders) {
        return inputProviders == null ? Set.of() : inputProviders.stream()
                .map(FlowRunFactory::resolveInputProvider)
                .collect(Collectors.toSet());
    }

    private static ContextStageAncestor resolveInputProvider(InputProvider inputProvider) {
        return inputProvider == null ? null : new ContextStageAncestor(inputProvider.getFlowStageId(), inputProvider.getOutputIds());
    }
}
