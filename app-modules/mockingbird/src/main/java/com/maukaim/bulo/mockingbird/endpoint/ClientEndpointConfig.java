package com.maukaim.bulo.mockingbird.endpoint;

import com.maukaim.bulo.app.commons.endpoints.ClientEventType;
import com.maukaim.bulo.app.commons.endpoints.controller.DefinitionClientEndpoint;
import com.maukaim.bulo.app.commons.endpoints.controller.FlowClientEndpoint;
import com.maukaim.bulo.app.commons.endpoints.controller.StageClientEndpoint;
import com.maukaim.bulo.app.commons.endpoints.controller.TriggerClientEndpoint;

import java.util.HashMap;
import java.util.Map;

public class ClientEndpointConfig {

    public static Map<ClientEventType, Class<?>> getClientEndpointClasses() {
        Map<ClientEventType, Class<?>> one = Map.of(
                ClientEventType.FLOW_CREATE_INSTRUCTION, FlowClientEndpoint.class,
                ClientEventType.FLOW_REMOVE_INSTRUCTION, FlowClientEndpoint.class,

                ClientEventType.STAGE_CREATE_INSTRUCTION, StageClientEndpoint.class,
                ClientEventType.STAGE_MULTIPLE_CREATE_INSTRUCTION, StageClientEndpoint.class,
                ClientEventType.STAGE_REMOVE_INSTRUCTION, StageClientEndpoint.class,
                ClientEventType.STAGE_GET_ALL, StageClientEndpoint.class,

                ClientEventType.STAGE_DEFINITION_CREATE_INSTRUCTION, DefinitionClientEndpoint.class,
                ClientEventType.DEFINITION_GET_ALL, DefinitionClientEndpoint.class
        );
        Map<ClientEventType, Class<?>> second = Map.of(
                ClientEventType.TRIGGER_MANUAL_ONETIME, TriggerClientEndpoint.class,
                ClientEventType.TRIGGER_ADD_SCHEDULED, TriggerClientEndpoint.class,
                ClientEventType.TRIGGER_REMOVE_SCHEDULED, TriggerClientEndpoint.class
        );

        return new HashMap<>() {{
            putAll(one);
            putAll(second);
        }};
    }
}
