package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FunctionalStageRunFactory {

    public static FunctionalStageRun updateState(FunctionalStageRun stageRun, OrchestrableContextStatus newStatus) {
        return new FunctionalStageRun(
                stageRun.getContextId(),
                stageRun.getContextualizedStageId(),
                stageRun.getContext(),
                newStatus,
                stageRun.getStageRunDependencies(),
                stageRun.getStartTime(),
                stageRun.getEndTime(),
                stageRun.getOutputProviders(),
                stageRun.getExecutionGraph(),
                stageRun.getStageRunsById());
    }

    public static FunctionalStageRun updateStageRunView(FunctionalStageRun stageRun, Map<String, StageRun<?>> mapOfViewToBeUpdated) {
        HashMap<String, StageRun<?>> newStageRunViewMap = new HashMap<>(stageRun.getStageRunsById());
        newStageRunViewMap.putAll(mapOfViewToBeUpdated);
        return new FunctionalStageRun(stageRun.getContextId(),
                stageRun.getContextualizedStageId(),
                stageRun.getContext(),
                stageRun.getStatus(),
                stageRun.getStageRunDependencies(),
                stageRun.getStartTime(),
                stageRun.getEndTime(),
                stageRun.getOutputProviders(),
                stageRun.getExecutionGraph(),
                Map.copyOf(newStageRunViewMap)
        );
    }

    public static FunctionalStageRun create(FunctionalStageDefinition definition,
                                            RunContext<?> runContext,
                                            ContextStageId contextStageId,
                                            Set<RunDependency> stageRunDependencies) {
        return new FunctionalStageRun(
                UUID.randomUUID().toString(),
                contextStageId,
                runContext,
                OrchestrableContextStatus.NEW,
                stageRunDependencies,
                null,
                null,
                Set.copyOf(definition.getOutputProviders()),
                new ExecutionGraph(resolveFlowStages(definition.getFunctionalSubStages())),
                new HashMap<>());
    }

    private static Map<ContextStageId, Set<ContextualizedStageDependency>> resolveFlowStages(Set<FsStage> fsStages) {
        return fsStages == null ? Map.of() : fsStages.stream()
                .collect(Collectors.toMap(
                        FsStage::getContextualizedId,
                        fsStage -> resolveInputDependencies(fsStage.getIoDependencies())
                ));
    }

    private static Set<ContextualizedStageDependency> resolveInputDependencies(Set<IoDependency> inputDependencies) {
        return inputDependencies == null ? Set.of() : inputDependencies.stream()
                .map(FunctionalStageRunFactory::resolveInputDependency)
                .collect(Collectors.toSet());
    }

    private static ContextualizedStageDependency resolveInputDependency(IoDependency inputDependency) {
        return inputDependency == null ? null : new ContextualizedStageDependency(
                inputDependency.getInputId(),
                resolveInputProviders(inputDependency.getInputProviders())
        );
    }

    private static Set<ContextStageAncestor> resolveInputProviders(Set<InputProvider> inputProviders) {
        return inputProviders == null ? Set.of() : inputProviders.stream()
                .map(FunctionalStageRunFactory::resolveInputProvider)
                .collect(Collectors.toSet());
    }

    private static ContextStageAncestor resolveInputProvider(InputProvider inputProvider) {
        return inputProvider == null ? null : new ContextStageAncestor(inputProvider.getFlowStageId(), inputProvider.getOutputIds());
    }
}
