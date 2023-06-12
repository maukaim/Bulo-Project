package com.maukaim.bulo.trigger.scheduler.core;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScheduleTriggerServiceTest {

    private CronExecutorService executorService;
    private TriggerConnector mockTriggerConnector;
    private ScheduleTriggerService scheduleTriggerService;
    private TriggerId triggerId;
    private String cronExpression;
    private Runnable task;
    private ScheduledFuture mockFuture;

    @BeforeEach
    public void setup() {
        executorService = mock(CronExecutorService.class);
        mockTriggerConnector = mock(TriggerConnector.class);
        scheduleTriggerService = new ScheduleTriggerService(executorService, mockTriggerConnector);
        triggerId = TriggerId.of("flowId", Set.of(ContextStageId.of("stageId", 0)));
        cronExpression = "0 0 * * * ?";
        task = () -> {};
        mockFuture = mock(ScheduledFuture.class);
    }

    @Test
    public void setTrigger_whenNewTriggerId_schedulesNewFuture() {
        when(executorService.schedule(any(Runnable.class), eq(cronExpression))).thenReturn(mockFuture);

        TriggerId triggerId = mock(TriggerId.class);
        scheduleTriggerService.setTrigger(triggerId, cronExpression);

        ArgumentCaptor<Runnable> argumentCaptor = ArgumentCaptor.forClass(Runnable.class);
        verify(executorService).schedule(argumentCaptor.capture(), eq(cronExpression));
        Runnable capturedRunnable = argumentCaptor.getValue();
        capturedRunnable.run();
        verify(triggerId).getFlowId();
        verify(triggerId).getFlowStageIds();
    }

    @Test
    public void setTrigger_whenExistingTriggerId_cancelsOldAndSchedulesNewFuture() {
        when(executorService.schedule(any(Runnable.class), eq(cronExpression))).thenReturn(mockFuture);

        scheduleTriggerService.setTrigger(triggerId, cronExpression);  // first call with triggerId
        scheduleTriggerService.setTrigger(triggerId, cronExpression);  // second call with triggerId

        verify(mockFuture).cancel(true);
        verify(executorService, times(2)).schedule(any(Runnable.class), eq(cronExpression));
    }

    @Test
    public void removeTrigger_whenExistingTriggerId_removesAndCancelsFuture() {
        when(executorService.schedule(any(Runnable.class), eq(cronExpression))).thenReturn(mockFuture);
        scheduleTriggerService.setTrigger(triggerId, cronExpression);

        boolean removed = scheduleTriggerService.removeTrigger(triggerId.getFlowId(), triggerId.getFlowStageIds());

        assertTrue(removed);
        verify(mockFuture).cancel(true);
    }

    @Test
    public void removeTrigger_whenNonExistingTriggerId_returnsFalse() {
        boolean removed = scheduleTriggerService.removeTrigger("nonExistingFlowId", Collections.emptySet());

        assertFalse(removed);
    }
}