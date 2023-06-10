package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class OutputsTSDValidatorTest {
    private final OutputsTSDValidator validator = new OutputsTSDValidator();
    private final TechnicalStageDefinition definition = mock(TechnicalStageDefinition.class);

    @Test
    public void validate_isPassThrough() {
        boolean result = validator.validate(definition);
        assertTrue(result);
    }
}