package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageDefinitionStore;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FunctionalStageDefinitionServiceImplTest {

    FunctionalStageDefinitionServiceImpl service;
    FunctionalStageDefinitionStore store;
    FunctionalStageDefinition definition;

    @BeforeEach
    void setUp() {
        // Here, I'm creating a mock of the dependencies
        store = mock(FunctionalStageDefinitionStore.class);
        definition = mock(FunctionalStageDefinition.class);
        service = new FunctionalStageDefinitionServiceImpl(store);
    }

    @Test
    void getById_whenDefinitionExists_thenReturnDefinition() {
        String definitionId = "testId";
        // Define behavior
        when(store.getById(definitionId)).thenReturn(definition);

        // Call test method
        FunctionalStageDefinition result = service.getById(definitionId);

        // Verify behavior
        verify(store).getById(definitionId);
        assertEquals(definition, result);
    }

    @Test
    void getById_whenDefinitionDoesNotExist_thenReturnNull() {
        String definitionId = "testId";
        // Define behavior
        when(store.getById(definitionId)).thenReturn(null);

        // Call test method
        FunctionalStageDefinition result = service.getById(definitionId);

        // Verify behavior
        verify(store).getById(definitionId);
        assertNull(result);
    }

    @Test
    void put_whenDefinitionIsNotNull_thenStoreDefinition() {
        // Call test method
        service.put(definition);

        // Verify behavior
        verify(store).put(definition);
    }

    @Test
    void remove_whenDefinitionExists_thenRemoveDefinition() {
        String definitionId = "testId";

        // Call test method
        service.remove(definitionId);

        // Verify behavior
        verify(store).remove(definitionId);
    }

}
