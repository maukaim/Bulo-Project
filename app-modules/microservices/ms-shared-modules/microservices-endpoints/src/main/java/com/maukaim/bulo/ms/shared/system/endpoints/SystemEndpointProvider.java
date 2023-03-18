package com.maukaim.bulo.ms.shared.system.endpoints;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.*;

import java.util.HashMap;
import java.util.Map;

public class SystemEndpointProvider {

    public static Map<MicroServiceEventType, Class<?>> getSystemEndpointClasses() {
        Map<MicroServiceEventType, Class<?>> one = Map.of(
                MicroServiceEventType.TRIGGER_FLOW_RUN, IFlowRunStartServiceEndpoint.class,
                MicroServiceEventType.DEF_CREATE_INSTRUCTION, IDefinitionCreateServiceEndpoint.class,
                MicroServiceEventType.NEED_STAGE_RUN_EVENT, IStageRunRequiredServiceEndpoint.class,
                MicroServiceEventType.FLOW_RUN_UPDATE, IFlowRunUpdateServiceEndpoint.class,
                MicroServiceEventType.STAGE_UPDATE, IStageUpdateServiceEndpoint.class,
                MicroServiceEventType.FLOW_UPDATE, IFlowUpdateServiceEndpoint.class,
                MicroServiceEventType.DEF_UPDATE, IDefinitionUpdateServiceEndpoint.class,
                MicroServiceEventType.EXECUTOR_UPDATE, IExecutorUpdateServiceEndpoint.class
        );
        Map<MicroServiceEventType, Class<?>> second = Map.of(
                MicroServiceEventType.STAGE_RUN_RESULT, IStageRunResultServiceEndpoint.class,
                MicroServiceEventType.STAGE_RUN_UPDATE, IStageRunUpdateServiceEndpoint.class,
                MicroServiceEventType.NEED_STAGE_RUN_CANCEL, IStageRunCancellationServiceEndpoint.class
        );

        return new HashMap<>() {{
            putAll(one);
            putAll(second);
        }};
    }
}
