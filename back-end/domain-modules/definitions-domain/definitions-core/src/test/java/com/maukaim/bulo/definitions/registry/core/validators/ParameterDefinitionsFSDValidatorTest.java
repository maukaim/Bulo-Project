package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParameterDefinitionsFSDValidatorTest {
    private ParameterDefinition definition1 = mock(ParameterDefinition.class);
    private ParameterDefinition definition2 = mock(ParameterDefinition.class);
    private List<ParameterDefinition> parameters = Arrays.asList(definition1, definition2);
    private FunctionalStageDefinition functionalStageDef = mock(FunctionalStageDefinition.class);
    private ParameterDefinitionsFSDValidator validator = new ParameterDefinitionsFSDValidator();

    @BeforeEach
    void init(){
        when(definition1.getName()).thenReturn("Param1");
        when(definition2.getName()).thenReturn("Param2");
        when(functionalStageDef.getParameters()).thenReturn(parameters);
    }

    @Test
    public void validate_whenParametersDoesNotHaveDuplicates_returnsTrue() {
        boolean result = validator.validate(functionalStageDef);
        assertTrue(result);
    }

    @Test
    public void validate_whenParametersEmpty_returnsTrue() {
        List<ParameterDefinition> parameters = Collections.emptyList();
        when(functionalStageDef.getParameters()).thenReturn(parameters);

        boolean result = validator.validate(functionalStageDef);

        assertTrue(result);
    }

    @Test
    public void validate_whenParametersNull_returnsFalse() {
        when(functionalStageDef.getParameters()).thenReturn(null);
        boolean result = validator.validate(functionalStageDef);

        assertFalse(result);
    }

    @Test
    public void validate_whenParametersHaveDuplicates_returnsFalse() {
        when(definition2.getName()).thenReturn("Param1");
        boolean result = validator.validate(functionalStageDef);

        assertFalse(result);
    }
}