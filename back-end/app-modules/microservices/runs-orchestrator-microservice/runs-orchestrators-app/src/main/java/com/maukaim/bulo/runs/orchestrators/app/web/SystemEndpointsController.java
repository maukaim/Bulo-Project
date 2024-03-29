package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.io.executors.system.StageRunEvent;
import com.maukaim.bulo.io.flows.system.FlowEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunStartEvent;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IDefinitionUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IFlowRunStartServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IFlowRunUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IFlowUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageRunUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageUpdateServiceEndpoint;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.DefinitionUpdateEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.StageRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.TriggerEventConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpointsController {

    @RestController
    class ServiceFlowRunStartEndpoint implements IFlowRunStartServiceEndpoint<FlowRunStartEvent> {
        private final TriggerEventConsumer triggerEventConsumer;

        @Autowired
        public ServiceFlowRunStartEndpoint(TriggerEventConsumer triggerEventConsumer) {
            this.triggerEventConsumer = triggerEventConsumer;
        }

        @Override
        public void consume(@RequestBody FlowRunStartEvent instruction) {
            new Thread(() -> triggerEventConsumer.onFlowRunInstruction(instruction)).start();
        }
    }

    @RestController
    class ServiceFlowRunUpdateEndpoint implements IFlowRunUpdateServiceEndpoint<FlowRunEvent> {
        private final FlowRunEventConsumer flowRunEventConsumer;

        public ServiceFlowRunUpdateEndpoint(FlowRunEventConsumer flowRunEventConsumer) {
            this.flowRunEventConsumer = flowRunEventConsumer;
        }

        @Override
        public void consume(FlowRunEvent flowRunEvent) {
            new Thread(() -> this.flowRunEventConsumer.consume(flowRunEvent)).start();
        }
    }

    @RestController
    class ServiceStageUpdateEndpoint implements IStageUpdateServiceEndpoint<StageUpdateEvent> {
        private final StageUpdateEventConsumer stageUpdateEventConsumer;

        ServiceStageUpdateEndpoint(StageUpdateEventConsumer stageUpdateEventConsumer) {
            this.stageUpdateEventConsumer = stageUpdateEventConsumer;
        }

        @Override
        public void consume(StageUpdateEvent event) {
            this.stageUpdateEventConsumer.consume(event);
        }
    }

    @RestController
    class ServiceFlowUpdateEndpoint implements IFlowUpdateServiceEndpoint<FlowEvent> {
        private final FlowEventConsumer flowEventConsumer;

        ServiceFlowUpdateEndpoint(FlowEventConsumer flowEventConsumer) {
            this.flowEventConsumer = flowEventConsumer;
        }

        @Override
        public void consume(FlowEvent flowEvent) {
            this.flowEventConsumer.consume(flowEvent);
        }
    }

    @RestController
    public class ServiceDefinitionUpdateEndpoint implements IDefinitionUpdateServiceEndpoint<StageDefinitionEvent> {
        private final DefinitionUpdateEventConsumer definitionUpdateEventConsumer;

        public ServiceDefinitionUpdateEndpoint(DefinitionUpdateEventConsumer definitionUpdateEventConsumer) {
            this.definitionUpdateEventConsumer = definitionUpdateEventConsumer;
        }

        @Override
        public void consume(StageDefinitionEvent event) {
            this.definitionUpdateEventConsumer.consume(event);
        }
    }

    @RestController
    public class ServiceStageRunUpdateEndpoint implements IStageRunUpdateServiceEndpoint<StageRunEvent> {
        private final StageRunEventConsumer stageRunEventConsumer;

        @Autowired
        public ServiceStageRunUpdateEndpoint(StageRunEventConsumer stageRunEventConsumer) {
            this.stageRunEventConsumer = stageRunEventConsumer;
        }

        public void consume(@RequestBody StageRunEvent event) {
            new Thread(() -> this.stageRunEventConsumer.consume(event)).start();
        }
    }
}
