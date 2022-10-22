package com.maukaim.bulo.flows.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.commons.models.ContextualizedStageId;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TestdefouTest {

    @Test
    public void tes() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<ContextualizedStageId, String> val = Map.of(
                ContextualizedStageId.of("Babar", 3), "jr"
        );
        objectMapper.writeValueAsString(val);
    }
}
