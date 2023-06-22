package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TechnicalStageRunFactoryTest {
    private TechnicalStageRunFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new TechnicalStageRunFactory();
    }

    @Test
    public void toBeRequested_whenRunContextAndStageIdAndStageRunDependenciesAreValid_toBeRequestedTechnicalStageRunCreated() {
        // Arrange
        RunContext<?> mockContext = mock(RunContext.class);
        ContextStageId mockStageId = mock(ContextStageId.class);
        Set<RunDependency> mockDependencies = Collections.singleton(mock(RunDependency.class));

        // Act
        TechnicalStageRun stageRun = factory.toBeRequested(mockContext, mockStageId, mockDependencies);

        // Assert
        assertEquals(TechnicalStageRunStatus.TO_BE_REQUESTED, stageRun.getStatus());
        assertEquals(mockStageId, stageRun.getContextualizedStageId());
        assertEquals(mockContext, stageRun.getContext());
        assertEquals(mockDependencies, stageRun.getStageRunDependencies());
    }

    @Test
    public void requested_whenPreviousViewIsValid_requestedTechnicalStageRunCreated() {
        // Arrange
        TechnicalStageRun mockPreviousView = mock(TechnicalStageRun.class);
        when(mockPreviousView.getStatus()).thenReturn(TechnicalStageRunStatus.TO_BE_REQUESTED);

        // Act
        TechnicalStageRun stageRun = factory.requested(mockPreviousView);

        // Assert
        assertEquals(TechnicalStageRunStatus.REQUESTED, stageRun.getStatus());
        assertEquals(mockPreviousView.getStageRunId(), stageRun.getStageRunId());
        assertEquals(mockPreviousView.getContextualizedStageId(), stageRun.getContextualizedStageId());
        assertEquals(mockPreviousView.getContext(), stageRun.getContext());
        assertEquals(mockPreviousView.getStageRunDependencies(), stageRun.getStageRunDependencies());
    }

    @Test
    public void acknowledged_whenPreviousViewIsValid_acknowledgedTechnicalStageRunCreated() {
        // Arrange
        TechnicalStageRun mockPreviousView = mock(TechnicalStageRun.class);
        String executorId = "executorId";

        // Act
        TechnicalStageRun stageRun = factory.acknowledged(mockPreviousView, executorId);

        // Assert
        assertEquals(TechnicalStageRunStatus.ACKNOWLEDGED, stageRun.getStatus());
        assertEquals(executorId, stageRun.getExecutorId());
    }

    @Test
    public void launched_whenPreviousViewAndStartTimeAreValid_launchedTechnicalStageRunCreated() {
        // Arrange
        TechnicalStageRun mockPreviousView = mock(TechnicalStageRun.class);
        Instant startTime = Instant.now();

        // Act
        TechnicalStageRun stageRun = factory.launched(mockPreviousView, startTime);

        // Assert
        assertEquals(TechnicalStageRunStatus.RUNNING, stageRun.getStatus());
        assertEquals(startTime, stageRun.getStartTime());
    }

    @Test
    public void toBeCancelled_whenPreviousViewIsValid_toBeCancelledTechnicalStageRunCreated() {
        // Arrange
        TechnicalStageRun mockPreviousView = mock(TechnicalStageRun.class);

        // Act
        TechnicalStageRun stageRun = factory.toBeCancelled(mockPreviousView);

        // Assert
        assertEquals(TechnicalStageRunStatus.TO_BE_CANCELLED, stageRun.getStatus());
    }

    @Test
    public void cancelled_whenPreviousViewAndEndTimeAreValid_cancelledTechnicalStageRunCreated() {
        // Arrange
        TechnicalStageRun mockPreviousView = mock(TechnicalStageRun.class);
        Instant endTime = Instant.now();
        when(mockPreviousView.getEndTime()).thenReturn(endTime.minusSeconds(5)); // Make sure previousView's endTime is before endTime

        // Act
        TechnicalStageRun stageRun = factory.cancelled(mockPreviousView, endTime);

        // Assert
        assertEquals(TechnicalStageRunStatus.CANCELLED, stageRun.getStatus());
        assertEquals(endTime, stageRun.getEndTime());
    }

    @Test
    public void failed_whenPreviousViewAndEndTimeAreValid_failedTechnicalStageRunCreated() {
        // Arrange
        TechnicalStageRun mockPreviousView = mock(TechnicalStageRun.class);
        Instant endTime = Instant.now();
        when(mockPreviousView.getEndTime()).thenReturn(endTime.minusSeconds(5)); // Make sure previousView's endTime is before endTime

        // Act
        TechnicalStageRun stageRun = factory.failed(mockPreviousView, endTime);

        // Assert
        assertEquals(TechnicalStageRunStatus.FAILED, stageRun.getStatus());
        assertEquals(endTime, stageRun.getEndTime());
    }

    @Test
    public void success_whenPreviousViewAndEndTimeAreValid_successTechnicalStageRunCreated() {
        // Arrange
        TechnicalStageRun mockPreviousView = mock(TechnicalStageRun.class);
        Instant endTime = Instant.now();
        when(mockPreviousView.getEndTime()).thenReturn(endTime.minusSeconds(5)); // Make sure previousView's endTime is before endTime

        // Act
        TechnicalStageRun stageRun = factory.success(mockPreviousView, endTime);

        // Assert
        assertEquals(TechnicalStageRunStatus.SUCCESS, stageRun.getStatus());
        assertEquals(endTime, stageRun.getEndTime());
    }
}
