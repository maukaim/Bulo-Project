package com.maukaim.bulo.ms.shared.system.endpoints;

import com.maukaim.bulo.ms.shared.system.endpoints.controllers.*;

import java.util.HashMap;
import java.util.Map;

public class SystemEndpointProvider {

    public static Map<ServiceEventType, Class<?>> getSystemEndpointClasses() {
        Map<ServiceEventType, Class<?>> one = Map.of(
                ServiceEventType.TRIGGER_FLOW_RUN, IFlowRunStartServiceEndpoint.class,
                ServiceEventType.DEF_CREATE_INSTRUCTION, IDefinitionCreateServiceEndpoint.class,
                ServiceEventType.NEED_STAGE_RUN_EVENT, IStageRunRequiredServiceEndpoint.class,
                ServiceEventType.FLOW_RUN_UPDATE, IFlowRunUpdateServiceEndpoint.class,
                ServiceEventType.STAGE_UPDATE, IStageUpdateServiceEndpoint.class,
                ServiceEventType.FLOW_UPDATE, IFlowUpdateServiceEndpoint.class,
                ServiceEventType.DEF_UPDATE, IDefinitionUpdateServiceEndpoint.class,
                ServiceEventType.EXECUTOR_UPDATE, IExecutorUpdateServiceEndpoint.class
        );
        Map<ServiceEventType, Class<?>> second = Map.of(
                ServiceEventType.STAGE_RUN_RESULT, IStageRunResultServiceEndpoint.class,
                ServiceEventType.STAGE_RUN_UPDATE, IStageRunUpdateServiceEndpoint.class,
                ServiceEventType.NEED_STAGE_RUN_CANCEL, IStageRunCancellationServiceEndpoint.class
        );

        return new HashMap<>() {{
            putAll(one);
            putAll(second);
        }};
    }
}
