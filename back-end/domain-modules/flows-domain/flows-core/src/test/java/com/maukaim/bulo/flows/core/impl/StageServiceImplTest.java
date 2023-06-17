package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StageServiceImplTest {
    private final StageStore stageStore = mock(StageStore.class);
    private StageService stageService;

    @BeforeEach
    void init() {
        stageService = new StageServiceImpl(stageStore);
    }

    @Test
    void getById_isPassThrough() {
        String stageId = "test";
        Stage stageExpected = mock(Stage.class);
        when(stageStore.getById(stageId)).thenReturn(stageExpected);

        Stage stageResult = stageService.getById(stageId);

        assertSame(stageExpected, stageResult);
        verify(stageStore).getById(stageId);
    }
}