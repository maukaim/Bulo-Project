package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.executors.data.lifecycle.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.executors.data.lifecycle.NeedStageRunEventConsumer;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultEventConsumer;
import com.maukaim.bulo.executors.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.io.executors.system.StageRunResultEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageRunCancellationServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageRunRequiredServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageRunResultServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageUpdateServiceEndpoint;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpointsController {
    @RestController
    public class ServiceStageRunRequiredEndpoint implements IStageRunRequiredServiceEndpoint<NeedStageRunExecutionEvent> {
        private final NeedStageRunEventConsumer needStageRunEventConsumer;

        public ServiceStageRunRequiredEndpoint(NeedStageRunEventConsumer needStageRunEventConsumer) {
            this.needStageRunEventConsumer = needStageRunEventConsumer;
        }

        @Override
        public void consume(@RequestBody NeedStageRunExecutionEvent instruction) {
            Thread thread = new Thread(() -> this.needStageRunEventConsumer.consume(instruction));
            thread.start();
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
    public class ServiceStageRunResultEndpoint implements IStageRunResultServiceEndpoint<StageRunResultEvent> {
        private final StageRunResultEventConsumer stageRunResultEventConsumer;

        public ServiceStageRunResultEndpoint(StageRunResultEventConsumer stageRunResultEventConsumer) {
            this.stageRunResultEventConsumer = stageRunResultEventConsumer;
        }

        @Override
        public void consume(StageRunResultEvent event) {
            this.stageRunResultEventConsumer.onStageRunResultEvent(event);
        }
    }

    @RestController
    public class ServiceStageRunCancellationEndpoint implements IStageRunCancellationServiceEndpoint<NeedStageRunCancellationEvent> {
        private final NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer;

        public ServiceStageRunCancellationEndpoint(NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer) {
            this.needStageRunCancelEventConsumer = needStageRunCancelEventConsumer;
        }

        @Override
        public void consume(NeedStageRunCancellationEvent event) {
            Thread thread = new Thread(() -> this.needStageRunCancelEventConsumer.consume(event));
            thread.start();
        }
    }
}
