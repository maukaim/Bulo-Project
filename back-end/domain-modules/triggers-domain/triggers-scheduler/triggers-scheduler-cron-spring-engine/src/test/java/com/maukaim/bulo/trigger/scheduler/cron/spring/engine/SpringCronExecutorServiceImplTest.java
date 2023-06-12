package com.maukaim.bulo.trigger.scheduler.cron.spring.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SpringCronExecutorServiceImplTest {

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private SpringCronExecutorServiceImpl cronExecutorService;

    @BeforeEach
    void setUp() {
        threadPoolTaskScheduler = mock(ThreadPoolTaskScheduler.class);
        cronExecutorService = new SpringCronExecutorServiceImpl(threadPoolTaskScheduler);
    }

    @Test
    void schedule_whenValidTaskAndCronExpression_schedulesTask() {
        Runnable task = () -> {};
        String cronExpression = "0 0 * * * ?";
        ScheduledFuture future = Mockito.mock(ScheduledFuture.class);

        when(threadPoolTaskScheduler.schedule(any(Runnable.class), any(CronTrigger.class))).thenReturn(future);

        ScheduledFuture<?> result = cronExecutorService.schedule(task, cronExpression);

        verify(threadPoolTaskScheduler).schedule(task, new CronTrigger(cronExpression));
        assertNotNull(result);
        assertEquals(future, result);
    }

    @Test
    void schedule_whenNullTask_throwsException() {
        String cronExpression = "0 0 * * * ?";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cronExecutorService.schedule(null, cronExpression);
        });

        assertEquals("Task cannot be null.", exception.getMessage());
    }

    @Test
    void schedule_whenInvalidCronExpression_throwsException() {
        Runnable task = () -> {};
        String cronExpression = "invalid_cron";

        assertThrows(IllegalArgumentException.class, () -> {
            cronExecutorService.schedule(task, cronExpression);
        });
    }
}