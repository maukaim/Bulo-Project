package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.ms.shared.system.endpoints.controllers.*;
import com.maukaim.bulo.runs.orchestrators.io.*;
import com.maukaim.bulo.runs.orchestrators.io.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpointsController {

    @RestController
    class ServiceFlowRunStartEndpoint implements IFlowRunStartServiceEndpoint<FlowRunInstruction> {
        private final TriggerEventConsumer triggerEventConsumer;

        @Autowired
        public ServiceFlowRunStartEndpoint(TriggerEventConsumer triggerEventConsumer) {
            this.triggerEventConsumer = triggerEventConsumer;
        }

        @Override
        public void consume(@RequestBody FlowRunInstruction instruction) {
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
            new Thread(() -> this.flowRunEventConsumer.onFlowRunEvent(flowRunEvent)).start();
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
            this.stageUpdateEventConsumer.onStageUpdate(event);
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
            this.flowEventConsumer.onFlowEvent(flowEvent);
        }
    }

    @RestController
    public class ServiceDefinitionUpdateEndpoint implements IDefinitionUpdateServiceEndpoint<DefinitionUpdateEvent> {
        private final DefinitionUpdateEventConsumer definitionUpdateEventConsumer;

        public ServiceDefinitionUpdateEndpoint(DefinitionUpdateEventConsumer definitionUpdateEventConsumer) {
            this.definitionUpdateEventConsumer = definitionUpdateEventConsumer;
        }

        @Override
        public void consume(DefinitionUpdateEvent event) {
            this.definitionUpdateEventConsumer.onDefinitionEvent(event);
        }
    }

    @RestController
    public class ServiceStageRunUpdateEndpoint implements IStageRunUpdateServiceEndpoint<BasicStageRunEvent> {
        private final StageRunEventConsumer stageRunEventConsumer;

        @Autowired
        public ServiceStageRunUpdateEndpoint(StageRunEventConsumer stageRunEventConsumer) {
            this.stageRunEventConsumer = stageRunEventConsumer;
        }

        public void consume(@RequestBody BasicStageRunEvent event) {
            new Thread(() -> this.stageRunEventConsumer.onStageRunEvent(event)).start();
        }
    }
}
