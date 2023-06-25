package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.utils.OrchestrableContextStatusResolver;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;


public class StageRunServiceImpl implements StageRunService {
    private final StageRunConnector stageRunConnector;
    private final StageRunStore stageRunStore;
    private final ExecutorService executorService;
    private final FunctionalStageService functionalStageService;
    private final FunctionalStageDefinitionService functionalStageDefinitionService;
    private final FunctionalStageRunFactory functionalStageRunFactory;
    private final TechnicalStageRunFactory technicalStageRunFactory;
    private final OrchestrableContextStatusResolver orchestrableContextStatusResolver;

    public StageRunServiceImpl(StageRunConnector stageRunConnector,
                               StageRunStore stageRunStore,
                               ExecutorService executorService,
                               FunctionalStageService functionalStageService,
                               FunctionalStageDefinitionService functionalStageDefinitionService,
                               FunctionalStageRunFactory functionalStageRunFactory,
                               TechnicalStageRunFactory technicalStageRunFactory,
                               OrchestrableContextStatusResolver orchestrableContextStatusResolver) {
        this.executorService = executorService;
        this.stageRunConnector = stageRunConnector;
        this.stageRunStore = stageRunStore;
        this.functionalStageService = functionalStageService;
        this.functionalStageDefinitionService = functionalStageDefinitionService;
        this.functionalStageRunFactory = functionalStageRunFactory;
        this.technicalStageRunFactory = technicalStageRunFactory;
        this.orchestrableContextStatusResolver = orchestrableContextStatusResolver;
    }

    public Map<String, StageRun<?>> getNextStageRuns(RunContext<?> runContext, Map<ContextStageId, Set<RunDependency>> stageToRunByDependencies) {
        Map<String, StageRun<?>> result = new HashMap<>();
        for (ContextStageId contextStageId : stageToRunByDependencies.keySet()) {
            Set<RunDependency> naiveRunDependencies = stageToRunByDependencies.get(contextStageId);
            Set<RunDependency> runDependencies = resolveRunDependenciesWithContext(runContext, naiveRunDependencies);
            StageRun<?> newRun;
            String definitionId = functionalStageService.getDefinitionId(contextStageId.getStageId());
            if (definitionId == null) {
                newRun = technicalStageRunFactory.toBeRequested(runContext, contextStageId, runDependencies);
            } else {
                FunctionalStageDefinition definition = functionalStageDefinitionService.getById(definitionId);
                newRun = functionalStageRunFactory.create(definition, runContext, contextStageId, runDependencies);
                System.out.println(String.format("For FS %s, stageDependencies are %s", contextStageId.getStageId(), runDependencies));
            }
            this.stageRunStore.put(newRun.getStageRunId(), newRun);
            result.put(newRun.getStageRunId(), newRun);
        }
        return result;
    }

    private Set<RunDependency> resolveRunDependenciesWithContext(RunContext<?> runContext, Set<RunDependency> naiveRunDependencies) {
        Map<String, RunDependency> contextRunDependencyMap = runContext.getStageRunDependencies().stream()
                .collect(Collectors.toMap(
                        RunDependency::getInputId,
                        runDependency -> runDependency
                ));
        HashSet<RunDependency> result = new HashSet<>();
        for (RunDependency runDependency : naiveRunDependencies) {
            if (runDependency.getAncestors() == null || runDependency.getAncestors().isEmpty()) {
                if (contextRunDependencyMap.containsKey(runDependency.getInputId())) {
                    result.add(contextRunDependencyMap.get(runDependency.getInputId()));
                } else {
                    throw new RuntimeException("Abnormal situation, if no ancestors, context should be providing smth.");
                }
            } else {
                result.add(runDependency);
            }
        }
        return result;
    }

    @Override
    public Map<String, StageRun<?>> startRuns(Collection<StageRun<?>> toBeRequestedStageRun) {
        Map<String, StageRun<?>> result = new HashMap<>();
        for (StageRun<?> stageRunToBeRequested : toBeRequestedStageRun) {
            Map<String, StageRun<?>> started;
            if (stageRunToBeRequested instanceof TechnicalStageRun) {
                started = startTechnicalStage((TechnicalStageRun) stageRunToBeRequested);
            } else if (stageRunToBeRequested instanceof FunctionalStageRun) {
                started = startFunctionalStage((FunctionalStageRun) stageRunToBeRequested);
            } else {
                throw new RuntimeException("Do not support other type than both up there. Please override this method.");
            }
            result.putAll(started);
        }

        return result;
    }

    private Map<String, StageRun<?>> startFunctionalStage(FunctionalStageRun functionalStageRun) {
        try {
            executorService.execute(() -> {
                try {
                    Map<String, StageRun<?>> stageRunsToRequest = this.getNextStageRuns(
                            functionalStageRun.toRunContext(),
                            resolveLocalRunDependenciesForRoots(functionalStageRun.getExecutionGraph().getRootsIds(), functionalStageRun));
                    this.computeStageRunUpdateUnderLock(functionalStageRun.getContextId(), (previous) -> stageRunsToRequest);
                } catch (Throwable t) {
                    System.out.println("Problem while scheduling execution: " + t);
                    functionalStageRunFactory.updateState(functionalStageRun, OrchestrableContextStatus.CANCELLED);
                }
            });
        } catch (Throwable t) {
            System.out.println("Problem while scheduling execution: " + t);
            functionalStageRunFactory.updateState(functionalStageRun, OrchestrableContextStatus.CANCELLED);
        }
        return Map.of();
    }

    private Map<ContextStageId, Set<RunDependency>> resolveLocalRunDependenciesForRoots(Set<ContextStageId> contextStageIds,
                                                                                        OrchestrableRunContext<?> orchestrableRunContext) {
        return contextStageIds == null ? Map.of() : contextStageIds.stream()
                .collect(Collectors
                        .toMap(
                                contextualizedStageId -> contextualizedStageId,
                                contextualizedStageId -> orchestrableRunContext.getExecutionGraph()
                                        .getFlowStageDependencies(contextualizedStageId).stream()
                                        .map(stageDependency -> new RunDependency(stageDependency.getInputId(), Set.of()))
                                        .collect(Collectors.toSet())
                        ));
    }

    private Map<String, StageRun<?>> startTechnicalStage(TechnicalStageRun stageRun) {
        boolean started = this.stageRunConnector.requestRun(stageRun.getContextualizedStageId().getStageId(), stageRun.getStageRunId(), stageRun.getStageRunDependencies());
        return Map.of(stageRun.getStageRunId(), started ?
                technicalStageRunFactory.requested(stageRun) :
                technicalStageRunFactory.failed(stageRun, Instant.now()));
    }

    @Override
    public StageRun<?> getById(String stageRunId) {
        return this.stageRunStore.getById(stageRunId);
    }

    @Override
    public void put(String stageRunId, StageRun<?> stageRun) {
        this.stageRunStore.put(stageRunId, stageRun);
    }

    @Override
    public void requestCancel(String stageRunId, String executorId) {
        StageRun<?> stageRun = this.getById(stageRunId);
        if (stageRun instanceof TechnicalStageRun) {
            this.stageRunConnector.requestCancel(stageRunId, executorId);
//            if (!requested) {
//                System.out.printf("LogTemp:::WARN Cancel request to executor %s did not succeed%n", executorId);
//            }
        } else if (stageRun instanceof FunctionalStageRun) {
            for (StageRun<?> inFlightStageRun : ((FunctionalStageRun) stageRun).getInFlightStageRuns()) {
                if (inFlightStageRun instanceof TechnicalStageRun) {
                    this.requestCancel(inFlightStageRun.getStageRunId(), ((TechnicalStageRun) inFlightStageRun).getExecutorId());
                } else {
                    this.requestCancel(inFlightStageRun.getStageRunId(), null);
                }
            }
        } else {
            throw new UnsupportedOperationException("Impossible, does not support following StageRun " + stageRun.getClass().getName());
        }
    }

    @Override
    public FunctionalStageRun computeStageRunUpdateUnderLock(String contextId, Function<FunctionalStageRun, Map<String, StageRun<?>>> contextUpdator) {
        AtomicReference<List<StageRun<?>>> toBeRequestedReference = new AtomicReference<>();
        AtomicReference<OrchestrableContextStatus> previousStatus = new AtomicReference<>();
        FunctionalStageRun stageRunPersisted = this.stageRunStore.compute(contextId, (id, functionalStageRun) -> {
            previousStatus.set(functionalStageRun.getStatus());

            Map<String, StageRun<?>> stageRunViewToUpdate = contextUpdator.apply(functionalStageRun);
            FunctionalStageRun newStageRun = functionalStageRunFactory.updateStageRunView(functionalStageRun, stageRunViewToUpdate);
            saveAllTechnicalStageRuns(stageRunViewToUpdate);
            newStageRun = functionalStageRunFactory.updateState(newStageRun, orchestrableContextStatusResolver.resolveStatus(newStageRun));

            List<StageRun<?>> tobeRequestedTechnicalStageRuns = stageRunViewToUpdate.values().stream()
                    .filter(stageRun -> stageRun.getStatus().isRunNeeded())
                    .collect(Collectors.toList());
            toBeRequestedReference.set(tobeRequestedTechnicalStageRuns);
            return newStageRun;
        });

        List<StageRun<?>> toBeRequestedRuns = toBeRequestedReference.get();
        FunctionalStageRun finalStageRun = requestAndResolve(contextId, stageRunPersisted, toBeRequestedRuns);
        propagateIfNewStatus(finalStageRun, previousStatus.get()); //ICI propagate dans les processors
        return finalStageRun;
    }

    private void saveAllTechnicalStageRuns(Map<String, StageRun<?>> stageRunViewToUpdate) {
        stageRunViewToUpdate.forEach((stageId, stageRun) -> {
            if (stageRun instanceof TechnicalStageRun) {
                this.put(stageId, stageRun);
            }
        });
    }

    private void propagateIfNewStatus(FunctionalStageRun finalStageRun, OrchestrableContextStatus oldStatus) {
        if (oldStatus == null || finalStageRun.getStatus() != oldStatus) {
            switch (finalStageRun.getStatus()) {
                case PENDING_START ->
                        this.stageRunConnector.propagateFunctionalStageRunAcknowleged(finalStageRun.getStageRunId());
                case RUNNING ->
                        this.stageRunConnector.propagateFunctionalStageRunStarted(finalStageRun.getStageRunId());
                case CANCELLED ->
                        this.stageRunConnector.propagateFunctionalStageRunCancelled(finalStageRun.getStageRunId());
                case FAILED -> this.stageRunConnector.propagateFunctionalStageRunFailed(finalStageRun.getStageRunId());
                case SUCCESS ->
                        this.stageRunConnector.propagateFunctionalStageRunSuccessful(finalStageRun.getStageRunId());
            }
        }
    }

    private FunctionalStageRun requestAndResolve(String contextId, FunctionalStageRun stageRunPersisted, List<StageRun<?>> toBeRequestedRuns) {
        if (toBeRequestedRuns != null && !toBeRequestedRuns.isEmpty()) {
            return this.stageRunStore.compute(contextId, (id, flowRun) -> {
                Map<String, StageRun<?>> stageRunsAfterRequest = this.startRuns(toBeRequestedRuns);
                FunctionalStageRun stageRunAfterRequested = functionalStageRunFactory.updateStageRunView(stageRunPersisted, stageRunsAfterRequest);
                stageRunAfterRequested = functionalStageRunFactory.updateState(stageRunAfterRequested, orchestrableContextStatusResolver.resolveStatus(stageRunAfterRequested));

                return stageRunAfterRequested;
            });
        }
        return stageRunPersisted;
    }
}
