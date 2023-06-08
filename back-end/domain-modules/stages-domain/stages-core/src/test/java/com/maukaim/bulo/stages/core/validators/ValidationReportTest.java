package com.maukaim.bulo.stages.core.validators;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationReportTest {
    @Test
    void no_whenCalledWithDetails_returnsInvalidReportWithDetails() {
        // Arrange
        List<String> details = List.of("Detail1", "Detail2");

        // Act
        ValidationReport report = ValidationReport.no(details);

        // Assert
        assertFalse(report.isValidated(), "Report should be invalid");
        assertEquals(details, report.getDetails(), "Report details should match provided details");
    }

    @Test
    void isValidated_whenDefaultSuccessReport_returnsTrue() {
        // Act
        boolean isValidated = ValidationReport.DEFAULT_SUCCESS_REPORT.isValidated();

        // Assert
        assertTrue(isValidated, "Default report should be valid");
    }

    @Test
    void getDetails_whenDefaultSuccessReport_returnsEmptyList() {
        // Act
        List<String> details = ValidationReport.DEFAULT_SUCCESS_REPORT.getDetails();

        // Assert
        assertTrue(details.isEmpty(), "Default report should have no details");
    }
}
