package com.maukaim.bulo.stages.core.impl;

import com.maukaim.bulo.stages.models.StageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StageDefinitionServiceImplTest {
    private StageDefinitionServiceImpl service;
    private StageDefinitionStore stageDefinitionStore = mock(StageDefinitionStore.class);
    private StageDefinition stageDefinition = mock(StageDefinition.class);

    @BeforeEach
    public void setup() {
        service = new StageDefinitionServiceImpl(stageDefinitionStore);
    }

    @Test
    public void put_whenStageDefinitionProvided_returnsSavedStageDefinition() {
        when(stageDefinitionStore.put(stageDefinition)).thenReturn(stageDefinition);

        StageDefinition result = service.put(stageDefinition);

        verify(stageDefinitionStore, times(1)).put(stageDefinition);
        assertEquals(stageDefinition, result);
    }

    @Test
    public void remove_whenStageDefinitionExists_returnsRemovedStageDefinition() {
        String definitionId = "testId";
        when(stageDefinitionStore.getById(definitionId)).thenReturn(stageDefinition);
        when(stageDefinitionStore.remove(stageDefinition)).thenReturn(stageDefinition);

        StageDefinition result = service.remove(definitionId);

        verify(stageDefinitionStore, times(1)).getById(definitionId);
        verify(stageDefinitionStore, times(1)).remove(stageDefinition);
        assertEquals(stageDefinition, result);
    }

    @Test
    public void remove_whenStageDefinitionNotFound_returnsNull() {
        String definitionId = "testId";
        when(stageDefinitionStore.getById(definitionId)).thenReturn(null);

        StageDefinition result = service.remove(definitionId);

        verify(stageDefinitionStore, times(1)).getById(definitionId);
        assertNull(result);
    }

    @Test
    public void getById_whenStageDefinitionExists_returnsStageDefinition() {
        String definitionId = "testId";
        when(stageDefinitionStore.getById(definitionId)).thenReturn(stageDefinition);

        StageDefinition result = service.getById(definitionId);

        verify(stageDefinitionStore, times(1)).getById(definitionId);
        assertEquals(stageDefinition, result);
    }

    @Test
    public void getAll_whenStageDefinitionsExist_returnsAllStageDefinitions() {
        List<StageDefinition> definitions = Arrays.asList(stageDefinition);
        when(stageDefinitionStore.getAll()).thenReturn(definitions);

        List<StageDefinition> result = service.getAll();

        verify(stageDefinitionStore, times(1)).getAll();
        assertEquals(definitions, result);
    }
}
