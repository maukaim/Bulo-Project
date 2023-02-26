package com.maukaim.bulo.app.endpoints.client;

import com.maukaim.bulo.app.shared.system.communication.api.SystemEventType;

public enum ClientEventType implements SystemEventType {
    DEFINITION_GET_ALL,
    STAGE_DEFINITION_CREATE_INSTRUCTION,

    FLOW_CREATE_INSTRUCTION,
    FLOW_REMOVE_INSTRUCTION,

    STAGE_GET_ALL,
    STAGE_CREATE_INSTRUCTION,
    STAGE_MULTIPLE_CREATE_INSTRUCTION,
    STAGE_REMOVE_INSTRUCTION,

    TRIGGER_MANUAL_ONETIME,
    TRIGGER_REMOVE_SCHEDULED,
    TRIGGER_ADD_SCHEDULED
}
