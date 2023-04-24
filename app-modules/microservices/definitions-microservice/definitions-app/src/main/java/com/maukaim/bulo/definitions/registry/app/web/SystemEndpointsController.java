package com.maukaim.bulo.definitions.registry.app.web;

import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;
import com.maukaim.bulo.data.lifecycle.definitions.client.CreateStageDefinitionConsumer;
import com.maukaim.bulo.definitions.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.definitions.ms.data.lifecycle.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.io.definitions.system.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IDefinitionCreateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IDefinitionUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IExecutorUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageUpdateServiceEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpointsController {

    @RestController
    class ServiceDefinitionCreateEndpoint implements IDefinitionCreateServiceEndpoint<CreateStageDefinitionInstruction> {
        private final CreateStageDefinitionConsumer declarationEventConsumer;

        @Autowired
        ServiceDefinitionCreateEndpoint(CreateStageDefinitionConsumer declarationEventConsumer) {
            this.declarationEventConsumer = declarationEventConsumer;
        }

        @Override
        public void consume(@RequestBody CreateStageDefinitionInstruction event) {
            this.declarationEventConsumer.consume(event);
        }
    }

    @RestController
    public class ServiceStageUpdateEndpoint implements IStageUpdateServiceEndpoint<StageUpdateEvent> {
        private final StageUpdateEventConsumer stageUpdateEventConsumer;

        public ServiceStageUpdateEndpoint(StageUpdateEventConsumer stageUpdateEventConsumer) {
            this.stageUpdateEventConsumer = stageUpdateEventConsumer;
        }

        @Override
        public void consume(StageUpdateEvent event) {
            this.stageUpdateEventConsumer.consume(event);
        }
    }

    @RestController
    public class ServiceDefinitionUpdateEndpoint implements IDefinitionUpdateServiceEndpoint<StageDefinitionEvent> {
        private final TechnicalStageDefinitionEventConsumer definitionEventConsumer;

        public ServiceDefinitionUpdateEndpoint(TechnicalStageDefinitionEventConsumer definitionEventConsumer) {
            this.definitionEventConsumer = definitionEventConsumer;
        }

        @Override
        public void consume(StageDefinitionEvent event) {
            this.definitionEventConsumer.consume(event);
        }
    }

    @RestController
    public class ServiceExecutorUpdateEndpoint implements IExecutorUpdateServiceEndpoint<ExecutorUpdateEvent> {
        private final TechnicalStageDefinitionEventConsumer definitionEventConsumer;

        public ServiceExecutorUpdateEndpoint(TechnicalStageDefinitionEventConsumer definitionEventConsumer) {
            this.definitionEventConsumer = definitionEventConsumer;
        }

        @Override
        public void consume(ExecutorUpdateEvent event) {
            this.definitionEventConsumer.consume(event);
        }
    }
}
