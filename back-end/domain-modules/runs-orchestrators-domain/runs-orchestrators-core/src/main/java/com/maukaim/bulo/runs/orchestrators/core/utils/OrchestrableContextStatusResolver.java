package com.maukaim.bulo.runs.orchestrators.core.utils;

import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

import java.util.Map;

public class OrchestrableContextStatusResolver {

    public OrchestrableContextStatus resolveStatus(OrchestrableRunContext<?> orchestrableRunContext) {
        OrchestrableContextStatus actualStatus = orchestrableRunContext.getStatus();
        if (actualStatus.isTerminal()) {
            return actualStatus;
        } else {
            Map<String, StageRun<?>> stageRunViewByStageId = orchestrableRunContext.getStageRunsById();
            boolean anyCancelled = false;
            boolean anyFailed = false;
            boolean anyRunning = false;
            boolean anyInProcessToStart = false;
            boolean anySuccessful = false;
            for (Map.Entry<String, StageRun<?>> stageRunViewById : stageRunViewByStageId.entrySet()) {
                StageRun<?> stageRun = stageRunViewById.getValue();
                switch (stageRun.getStageType()){
                    case TECHNICAL -> {
                        switch (((TechnicalStageRun) stageRun).getStatus()) {
                            case RUNNING -> anyRunning = true;
                            case CANCELLED -> anyCancelled = true;
                            case FAILED -> anyFailed = true;
                            case ACKNOWLEDGED -> anyInProcessToStart = true;
                            case SUCCESS -> anySuccessful = true;
                        }
                    }
                    case FUNCTIONAL -> {
                        switch (((FunctionalStageRun) stageRun).getStatus()){
                            case RUNNING -> anyRunning = true;
                            case CANCELLED -> anyCancelled = true;
                            case FAILED -> anyFailed = true;
                            case PENDING_START -> anyInProcessToStart = true;
                            case SUCCESS -> anySuccessful = true;
                        }
                    }
                }
            }

            if (anyFailed) return OrchestrableContextStatus.FAILED;
            else if (anyCancelled) return OrchestrableContextStatus.CANCELLED;
            else if (anyRunning) return OrchestrableContextStatus.RUNNING;
            else if (orchestrableRunContext.allRunsAreTerminated()) return OrchestrableContextStatus.SUCCESS;
            else if (OrchestrableContextStatus.NEW.equals(actualStatus) && (anyInProcessToStart)) return OrchestrableContextStatus.PENDING_START;
            else if (anySuccessful) return OrchestrableContextStatus.RUNNING;
            else return actualStatus;
        }
    }
}
