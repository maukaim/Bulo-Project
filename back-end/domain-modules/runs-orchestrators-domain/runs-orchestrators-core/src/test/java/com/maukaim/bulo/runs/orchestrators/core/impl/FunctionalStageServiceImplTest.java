package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageService;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FunctionalStageServiceImplTest {
    FunctionalStageStore functionalStageStore = mock(FunctionalStageStore.class);
    FunctionalStageService functionalStageService = new FunctionalStageServiceImpl(functionalStageStore);

    @Test
    void getDefinitionId_whenIdExists_returnsDefinitionId() {
        // Arrange
        String id = "testId";
        String expectedDefinitionId = "expectedDefinitionId";
        when(functionalStageStore.getDefinitionId(id)).thenReturn(expectedDefinitionId);

        // Act
        String actualDefinitionId = functionalStageService.getDefinitionId(id);

        // Assert
        assertEquals(expectedDefinitionId, actualDefinitionId);
        verify(functionalStageStore, times(1)).getDefinitionId(id);
    }

    @Test
    void getDefinitionId_whenIdIsNull_throwsException() {
        // Arrange
        String id = null;
        when(functionalStageStore.getDefinitionId(id)).thenThrow(new IllegalArgumentException());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> functionalStageService.getDefinitionId(id));
        verify(functionalStageStore, times(1)).getDefinitionId(id);
    }
}