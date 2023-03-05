package com.maukaim.bulo.ms.shared.system.endpoints;

import com.maukaim.bulo.app.shared.system.communication.api.SystemEventType;

/**
 * All event types (either a single event or a group of events) transiting in the system.
 */
public enum ServiceEventType implements SystemEventType {
    //from Triggers service
    TRIGGER_FLOW_RUN,

    //From Orchestrator service
    NEED_STAGE_RUN_EVENT,
    NEED_STAGE_RUN_CANCEL,
    FLOW_RUN_UPDATE,

    //From Stages service
    STAGE_UPDATE,
    //From Flows service
    FLOW_UPDATE,

    //From Definitions service
    DEF_UPDATE,
    EXECUTOR_UPDATE,

    //From Executors service
    STAGE_RUN_RESULT,
    STAGE_RUN_UPDATE,
    DEF_CREATE_INSTRUCTION
}
