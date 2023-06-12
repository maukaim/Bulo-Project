package com.maukaim.bulo.commons.utils;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeHelperTest {

    @Test
    void isBefore_whenRefIsNull_ReturnsFalse() {
        Instant ref = null;
        Instant other = Instant.now();
        assertFalse(TimeHelper.isBefore(ref, other));
    }

    @Test
    void isBefore_whenOtherIsNull_ReturnsTrue() {
        Instant ref = Instant.now();
        Instant other = null;
        assertTrue(TimeHelper.isBefore(ref, other));
    }

    @Test
    void isBefore_whenRefIsBeforeOther_ReturnsTrue() {
        Instant ref = Instant.now();
        Instant other = ref.plusMillis(1);
        assertTrue(TimeHelper.isBefore(ref, other));
    }

    @Test
    void isBefore_whenRefIsNotBeforeOther_ReturnsFalse() {
        Instant ref = Instant.now();
        Instant other = ref.minusMillis(1);
        assertFalse(TimeHelper.isBefore(ref, other));
    }

    @Test
    void isAfter_whenRefIsNull_ReturnsFalse() {
        Instant ref = null;
        Instant other = Instant.now();
        assertFalse(TimeHelper.isAfter(ref, other));
    }

    @Test
    void isAfter_whenOtherIsNull_ReturnsTrue() {
        Instant ref = Instant.now();
        Instant other = null;
        assertTrue(TimeHelper.isAfter(ref, other));
    }

    @Test
    void isAfter_whenRefIsAfterOther_ReturnsTrue() {
        Instant ref = Instant.now();
        Instant other = ref.minusMillis(1);
        assertTrue(TimeHelper.isAfter(ref, other));
    }

    @Test
    void isAfter_whenRefIsNotAfterOther_ReturnsFalse() {
        Instant ref = Instant.now();
        Instant other = ref.plusMillis(1);
        assertFalse(TimeHelper.isAfter(ref, other));
    }
}