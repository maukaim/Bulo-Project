package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefinitionServiceImplTest {
    private final StageDefinitionStore stageDefinitionStore = mock(StageDefinitionStore.class);
    private DefinitionService definitionService;
    @BeforeEach
    void init(){
        definitionService = new DefinitionServiceImpl(stageDefinitionStore);
    }

    @Test
    void get(){
        String definitionId = "test";
        StageDefinition expectedResult = mock(StageDefinition.class);
        when(stageDefinitionStore.getById(definitionId)).thenReturn(expectedResult);
        StageDefinition stageDefinition = definitionService.getById(definitionId);

        assertSame(expectedResult, stageDefinition);
        verify(stageDefinitionStore).getById(definitionId);
    }

}