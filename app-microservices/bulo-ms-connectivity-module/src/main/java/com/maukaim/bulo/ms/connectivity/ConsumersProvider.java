package com.maukaim.bulo.ms.connectivity;

import java.util.List;

public class ConsumersProvider {

    public static List<Consumer> getConsumers(EventType eventType) {
        return switch (eventType) {
            case TRIGGER -> List.of(
                    new Consumer(Services.ORCHESTRATOR_SERVICE, "api/v1/orchestrator/flowRuns/startFlowRun")
            );
            case NEED_STAGE_RUN_EVENT -> List.of(
                    new Consumer(Services.EXECUTORS_SERVICE, "api/v1/executors/runs/needRun")
            );
            case FLOW_RUN_UPDATE -> List.of(
                    new Consumer(Services.ORCHESTRATOR_SERVICE, "api/v1/orchestrator/flowRuns/onUpdate")
            );
            case STAGE_UPDATE -> List.of(
                    new Consumer(Services.STAGES_SERVICE, "api/v1/stages/onUpdateEvent"),
                    new Consumer(Services.FLOWS_SERVICE, "api/v1/flows/stages/createOrUpdate"),
                    new Consumer(Services.EXECUTORS_SERVICE, "api/v1/executors/stages/onEvent")
            );
            case FLOW_UPDATE -> List.of(
                    new Consumer(Services.FLOWS_SERVICE, "api/v1/flows/flowUpdate"),
                    new Consumer(Services.ORCHESTRATOR_SERVICE, "api/v1/orchestrator/flows/onUpdate")
            );
            case DEF_UPDATE -> List.of(
                    new Consumer(Services.DEFINITIONS_SERVICE, "api/v1/definitions/onUpdateEvent"),
                    new Consumer(Services.STAGES_SERVICE, "api/v1/definitions/onEvent"),
                    new Consumer(Services.FLOWS_SERVICE, "api/v1/flows/definitions/onUpdate")
            );
            case STAGE_RUN_RESULT -> List.of(
                    new Consumer(Services.EXECUTORS_SERVICE, "api/v1/executors/results/onResultEvent")
            );
            case STAGE_RUN_UPDATE -> List.of(
                    new Consumer(Services.ORCHESTRATOR_SERVICE, "api/v1/orchestrator/stageRuns/onStageRunEvent")
            );
            case DEF_CREATE_INSTRUCTION -> List.of(
                    new Consumer(Services.DEFINITIONS_SERVICE, "api/v1/definitions/register")
            );
            case NEED_STAGE_RUN_CANCEL -> List.of(
                    new Consumer(Services.EXECUTORS_SERVICE, "api/v1/executors/runs/needCancel")
            );
        };
    }
}
