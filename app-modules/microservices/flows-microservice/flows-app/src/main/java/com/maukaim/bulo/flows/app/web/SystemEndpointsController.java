package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.flows.ms.data.lifecycle.FlowEventConsumer;
import com.maukaim.bulo.flows.ms.data.lifecycle.StageDefinitionConsumer;
import com.maukaim.bulo.flows.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.io.flows.system.FlowEvent;
import com.maukaim.bulo.io.stages.system.events.StageUpdateEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IDefinitionUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IFlowUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageUpdateServiceEndpoint;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpointsController {

    @RestController
    public class ServiceStageUpdateEndpoint implements IStageUpdateServiceEndpoint<StageUpdateEvent> {
        private final StageUpdateEventConsumer stageUpdateEventConsumer;

        public ServiceStageUpdateEndpoint(StageUpdateEventConsumer stageUpdateEventConsumer) {
            this.stageUpdateEventConsumer = stageUpdateEventConsumer;
        }

        @Override
        public void consume(StageUpdateEvent event) {
            this.stageUpdateEventConsumer.onStageUpdate(event);
        }
    }

    @RestController
    public class ServiceFlowUpdateEndpoint implements IFlowUpdateServiceEndpoint<FlowEvent> {
        private FlowEventConsumer flowEventConsumer;

        public ServiceFlowUpdateEndpoint(FlowEventConsumer flowEventConsumer) {
            this.flowEventConsumer = flowEventConsumer;
        }

        @Override
        public void consume(FlowEvent event) {
            this.flowEventConsumer.onFlowEvent(event);
        }
    }

    @RestController
    public class ServiceDefinitionUpdateEndpoint implements IDefinitionUpdateServiceEndpoint<StageDefinitionEvent> {
        private final StageDefinitionConsumer stageDefinitionConsumer;

        public ServiceDefinitionUpdateEndpoint(StageDefinitionConsumer stageDefinitionConsumer) {
            this.stageDefinitionConsumer = stageDefinitionConsumer;
        }

        public void consume(@RequestBody StageDefinitionEvent event) {
            this.stageDefinitionConsumer.consume(event);
        }
    }
}
