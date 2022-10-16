package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.*;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlowRunFactory {

    public static FlowRun updateState(FlowRun flowRun, FlowRunStatus newStatus) {
        return new FlowRun(flowRun.getFlowRunId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), flowRun.getStageRunsById(), newStatus);
    }

    public static FlowRun updateStageRunView(FlowRun flowRun, Map<String, StageRun> mapOfViewToBeUpdated) {
        HashMap<String, StageRun> newStageRunViewMap = new HashMap<>(flowRun.getStageRunsById());
        newStageRunViewMap.putAll(mapOfViewToBeUpdated);
        return new FlowRun(flowRun.getFlowRunId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), Map.copyOf(newStageRunViewMap), flowRun.getFlowRunStatus());
    }

    public static FlowRun create(Flow flow) {
        return new FlowRun(UUID.randomUUID().toString(),
                flow.getFlowId(),
                new ExecutionGraph(resolveFlowStages(flow.getFlowStages())),
                new HashMap<>(),
                FlowRunStatus.NEW);
    }

    private static Map<FlowStageId, Set<FlowStageDependency>> resolveFlowStages(Set<FlowStage> flowStages) {
        return flowStages == null ? Map.of() : flowStages.stream()
                .collect(Collectors.toMap(
                        FlowStage::getFlowStageId,
                        flowStage -> resolveInputDependencies(flowStage.getIoDependencies())
                ));
    }

    private static Set<FlowStageDependency> resolveInputDependencies(Set<InputDependency> inputDependencies) {
        return inputDependencies == null ? Set.of() : inputDependencies.stream()
                .map(FlowRunFactory::resolveInputDependency)
                .collect(Collectors.toSet());
    }

    private static FlowStageDependency resolveInputDependency(InputDependency inputDependency) {
        return inputDependency == null ? null : new FlowStageDependency(
                inputDependency.getInputId(),
                resolveInputProviders(inputDependency.getInputProviders())
        );
    }

    private static Set<FlowStageAncestor> resolveInputProviders(Set<InputProvider> inputProviders) {
        return inputProviders == null ? Set.of() : inputProviders.stream()
                .map(FlowRunFactory::resolveInputProvider)
                .collect(Collectors.toSet());
    }

    private static FlowStageAncestor resolveInputProvider(InputProvider inputProvider) {
        return inputProvider == null ? null : new FlowStageAncestor(inputProvider.getFlowStageId(), inputProvider.getOutputIds());
    }
}
