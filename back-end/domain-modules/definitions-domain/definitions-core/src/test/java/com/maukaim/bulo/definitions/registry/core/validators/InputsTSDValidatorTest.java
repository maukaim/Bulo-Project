package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InputsTSDValidatorTest {
    private final InputsTSDValidator validator = new InputsTSDValidator();
    private final TechnicalStageDefinition definition = mock(TechnicalStageDefinition.class);

    @Test
    public void validate_isPassThrough() {
        boolean result = validator.validate(definition);
        assertTrue(result);
    }
}