package com.maukaim.bulo.commons.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AcyclicValidatorTest {
    private AcyclicValidator<Integer> validator;

    @BeforeEach
    public void setup() {
        validator = new AcyclicValidator<>();
    }

    @Test
    public void validate_whenEmptyMap_noExceptionThrown() {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        assertDoesNotThrow(() -> validator.validate(map));
    }

    @Test
    public void validate_whenSingleElementMap_noExceptionThrown() {
        Map<Integer, Set<Integer>> map = Map.of(1, Set.of());

        assertDoesNotThrow(() -> validator.validate(map));
    }

    @Test
    public void validate_whenAcyclicGraph_noExceptionThrown() {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        map.put(1, Set.of(2));
        map.put(2, Set.of(3));
        map.put(3, Set.of());

        assertDoesNotThrow(() -> validator.validate(map));
    }

    @Test
    public void validate_whenCyclicGraph_exceptionThrown() {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        map.put(1, Set.of(2));
        map.put(2, Set.of(3));
        map.put(3, Set.of(1));

        assertThrows(IllegalArgumentException.class, () -> validator.validate(map));
    }
}