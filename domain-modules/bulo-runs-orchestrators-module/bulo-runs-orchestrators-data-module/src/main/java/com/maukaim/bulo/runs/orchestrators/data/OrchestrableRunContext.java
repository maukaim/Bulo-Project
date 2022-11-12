package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class OrchestrableRunContext<KEY> {
    abstract public RunContextType getContextType();

    abstract public KEY getContextId();

    abstract public OrchestrableContextStatus getStatus();

    abstract public ExecutionGraph getExecutionGraph();

    //TODO: StageRun should not be there, it should be in StageRunService, here only StageRunIds !!
    abstract public Map<String, StageRun> getStageRunsById();

    abstract public RunContext<KEY> toRunContext();


    public Set<StageRun> getInFlightStageRuns() {
        return this.getStageRunsById().values().stream()
                .filter(stageRunView -> !stageRunView.isTerminated())
                .collect(Collectors.toUnmodifiableSet());
    }

    public boolean otherAncestorsAreSuccessful(ContextStageId stageId, ContextStageId ancestorExcludedId) {
        return this.getExecutionGraph().getAncestors(stageId).stream()
                .filter(ancestor -> !ancestorExcludedId.equals(ancestor))
                .allMatch(this::stageIsSuccessfullyTerminated);
    }

    private boolean stageIsSuccessfullyTerminated(ContextStageId stageId) {
        Optional<StageRun> stageRunView = this.getStageRunsById().values().stream()
                .filter(stageRun -> stageId.equals(stageRun.getContextStageId()))
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

    public Map<ContextStageId, StageRun> getLeavesRuns(){
        if(!getStatus().isSuccess()){
            throw new RuntimeException("Impossible to get Leave Runs if status not terminal.");
        }

        Map<ContextStageId, StageRun> stageRunsByContextStageId = this.getAllStageRuns().stream().collect(Collectors.toMap(
                stageRun -> stageRun.getContextStageId(),
                stageRun -> stageRun
        ));

        return this.getExecutionGraph().getLeavesIds().stream()
                .collect(Collectors.toMap(
                        leaveId -> leaveId,
                        leaveId -> stageRunsByContextStageId.get(leaveId)
                ));

    };

}
