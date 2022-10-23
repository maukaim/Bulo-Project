package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class OrchestrableContext<KEY> {
    abstract public RunContextType getContextType();

    abstract public KEY getContextId();

    abstract public OrchestrableContextStatus getStatus();

    abstract public ExecutionGraph getExecutionGraph();

    //TODO: StageRun should not be there, it should be in StageRunService, here only StageRunIds !!
    abstract public Map<String, StageRun> getStageRunsById();

    public Set<StageRun> getInFlightStageRuns() {
        return this.getStageRunsById().values().stream()
                .filter(stageRunView -> !stageRunView.isTerminated())
                .collect(Collectors.toUnmodifiableSet());
    }

    public boolean otherAncestorsAreSuccessful(ContextualizedStageId stageId, ContextualizedStageId ancestorExcludedId) {
        return this.getExecutionGraph().getAncestors(stageId).stream()
                .filter(ancestor -> !ancestorExcludedId.equals(ancestor))
                .allMatch(this::stageIsSuccessfullyTerminated);
    }

    private boolean stageIsSuccessfullyTerminated(ContextualizedStageId stageId) {
        Optional<StageRun> stageRunView = this.getStageRunsById().values().stream()
                .filter(stageRun -> stageId.equals(stageRun.getContextualizedStageId()))
                .findFirst();
        return stageRunView.isPresent() && stageRunView.get().getStatus().isSuccess();
    }

    public boolean allRunsAreTerminated() {
        return this.getStageRunsById().values().stream().allMatch(StageRun::isTerminated);
    }

    public Set<String> getStageRunIds() {
        return getStageRunsById().keySet();
    }

    public Collection<StageRun> getAllStageRuns() {
        return getStageRunsById().values();
    }
}
