package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.runners.api.StageRunner;
import com.maukaim.bulo.runners.api.models.StageDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StageRunnerRegistryImplTest {
    private StageRunnerRegistryImpl stageRunnerRegistry;
    private Map<String, StageRunner> defaultRunners;
    private StageRunner stageRunner;

    @BeforeEach
    void setUp() {
        stageRunner = mock(StageRunner.class);
        defaultRunners = new HashMap<>();
        defaultRunners.put("test", stageRunner);
        stageRunnerRegistry = new StageRunnerRegistryImpl(defaultRunners);
    }

    @Test
    void getAllDefinitions_whenRegistryIsNotEmpty_returnAllDefinitions() {
        StageDefinition stageDefinition = mock(StageDefinition.class);
        when(stageRunner.getDefinition()).thenReturn(stageDefinition);
        assertFalse(stageRunnerRegistry.getAllDefinitions().isEmpty());
    }

    @Test
    void getAllDefinitions_whenRegistryIsEmpty_returnEmptyList() {
        stageRunnerRegistry = new StageRunnerRegistryImpl(new HashMap<>());
        assertTrue(stageRunnerRegistry.getAllDefinitions().isEmpty());
    }

    @Test
    void getByDefinitionId_whenIdExists_returnRunner() {
        assertEquals(stageRunner, stageRunnerRegistry.getByDefinitionId("test"));
    }

    @Test
    void getByDefinitionId_whenIdDoesNotExist_returnNull() {
        assertNull(stageRunnerRegistry.getByDefinitionId("wrong-id"));
    }

    @Test
    void add_whenIdDoesNotExist_addsNewRunner() {
        StageRunner newRunner = mock(StageRunner.class);
        stageRunnerRegistry.add("newId", newRunner);
        assertEquals(newRunner, stageRunnerRegistry.getByDefinitionId("newId"));
    }

    @Test
    void add_whenIdAlreadyExists_doesNotChangeExistingRunner() {
        StageRunner newRunner = mock(StageRunner.class);
        stageRunnerRegistry.add("test", newRunner);
        assertEquals(stageRunner, stageRunnerRegistry.getByDefinitionId("test"));
    }

    @Test
    void drop_whenIdExists_removesRunner() {
        stageRunnerRegistry.drop("test");
        assertNull(stageRunnerRegistry.getByDefinitionId("test"));
    }

    @Test
    void drop_whenIdDoesNotExist_returnsNull() {
        assertNull(stageRunnerRegistry.drop("wrong-id"));
    }
}