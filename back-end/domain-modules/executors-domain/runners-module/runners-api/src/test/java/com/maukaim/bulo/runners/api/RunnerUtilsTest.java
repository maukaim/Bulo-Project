package com.maukaim.bulo.runners.api;

import com.maukaim.bulo.runners.api.models.StageInputDefinition;
import com.maukaim.bulo.runners.api.models.StageOutputDefinition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RunnerUtilsTest {
    @Test
    void toMap_whenStageInputDefinitionArrayIsNotEmpty_returnMap() {
        StageInputDefinition stageInputDefinition = mock(StageInputDefinition.class);
        when(stageInputDefinition.getName()).thenReturn("TestName");

        Map<String, StageInputDefinition> resultMap = RunnerUtils.toMap(stageInputDefinition);

        assertFalse(resultMap.isEmpty());
        assertTrue(resultMap.containsKey("TestName"));
        assertEquals(stageInputDefinition, resultMap.get("TestName"));
    }

    @Test
    void toMap_whenStageInputDefinitionArrayHasNullElement_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> RunnerUtils.toMap((StageInputDefinition) null));
    }

    @Test
    void toMap_whenStageOutputDefinitionArrayIsNotEmpty_returnMap() {
        StageOutputDefinition stageOutputDefinition = mock(StageOutputDefinition.class);
        when(stageOutputDefinition.getName()).thenReturn("TestName");

        Map<String, StageOutputDefinition> resultMap = RunnerUtils.toMap(stageOutputDefinition);

        assertFalse(resultMap.isEmpty());
        assertTrue(resultMap.containsKey("TestName"));
        assertEquals(stageOutputDefinition, resultMap.get("TestName"));
    }

    @Test
    void toMap_whenStageOutputDefinitionArrayHasNullElement_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> RunnerUtils.toMap((StageOutputDefinition) null));
    }
}
