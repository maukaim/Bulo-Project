package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.io.executors.system.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.io.executors.system.NeedStageRunEventConsumer;
import com.maukaim.bulo.io.executors.system.StageRunResultEventConsumer;
import com.maukaim.bulo.io.executors.system.StageUpdateEventConsumer;
import com.maukaim.bulo.io.executors.system.in.CancelRunInstruction;
import com.maukaim.bulo.io.executors.system.in.RunInstruction;
import com.maukaim.bulo.io.executors.system.in.StageUpdateEvent;
import com.maukaim.bulo.io.executors.system.out.StageRunResultEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageRunCancellationServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageRunRequiredServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageRunResultServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageUpdateServiceEndpoint;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpointsController {
    @RestController
    public class ServiceStageRunRequiredEndpoint implements IStageRunRequiredServiceEndpoint<RunInstruction> {
        private final NeedStageRunEventConsumer needStageRunEventConsumer;

        public ServiceStageRunRequiredEndpoint(NeedStageRunEventConsumer needStageRunEventConsumer) {
            this.needStageRunEventConsumer = needStageRunEventConsumer;
        }

        @Override
        public void consume(@RequestBody RunInstruction instruction) {
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
            this.stageUpdateEventConsumer.onStageUpdateEvent(event);
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
    public class ServiceStageRunCancellationEndpoint implements IStageRunCancellationServiceEndpoint<CancelRunInstruction> {
        private final NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer;

        public ServiceStageRunCancellationEndpoint(NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer) {
            this.needStageRunCancelEventConsumer = needStageRunCancelEventConsumer;
        }

        @Override
        public void consume(CancelRunInstruction event) {
            Thread thread = new Thread(() -> this.needStageRunCancelEventConsumer.consume(event));
            thread.start();
        }
    }
}
