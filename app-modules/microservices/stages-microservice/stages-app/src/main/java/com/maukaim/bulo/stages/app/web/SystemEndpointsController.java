package com.maukaim.bulo.stages.app.web;

import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IDefinitionUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageUpdateServiceEndpoint;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageDefinitionEventConsumer;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageUpdateEventConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpointsController {

    @RestController
    class ServiceStageUpdateEndpoint implements IStageUpdateServiceEndpoint<StageUpdateEvent> {
        private final StageUpdateEventConsumer consumer;

        @Autowired
        ServiceStageUpdateEndpoint(StageUpdateEventConsumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public void consume(@RequestBody StageUpdateEvent stageUpdateEvent) {
            this.consumer.consume(stageUpdateEvent);
        }
    }

    @RestController
    public class ServiceDefinitionUpdateEndpoint implements IDefinitionUpdateServiceEndpoint<StageDefinitionEvent> {
        private final StageDefinitionEventConsumer definitionEventConsumer;

        public ServiceDefinitionUpdateEndpoint(StageDefinitionEventConsumer definitionEventConsumer) {
            this.definitionEventConsumer = definitionEventConsumer;
        }

        @Override
        public void consume(StageDefinitionEvent event) {
            this.definitionEventConsumer.consume(event);
        }
    }
}
