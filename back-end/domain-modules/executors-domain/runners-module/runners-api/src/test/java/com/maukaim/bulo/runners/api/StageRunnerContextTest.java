package com.maukaim.bulo.runners.api;

import com.maukaim.bulo.api.data.types.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StageRunnerContextTest {

    private StageRunnerContext context;
    private Map<String, String> inputs;
    private Map<String, String> parameters;
    private RunnerMarshaller marshaller;

    @BeforeEach
    public void setUp() {
        inputs = mock(Map.class);
        parameters = mock(Map.class);
        marshaller = mock(RunnerMarshaller.class);
        context = new StageRunnerContext(inputs, parameters, marshaller);
    }

    @Test
    public void getRawParameter_whenExistsInParameters_returnParameterValue() {
        when(parameters.get("key")).thenReturn("value");
        assertEquals("value", context.getRawParameter("key"));
    }

    @Test
    public void getRawParameter_whenDoesntExistInParameters_returnNull() {
        when(parameters.get("key")).thenReturn(null);
        assertNull(context.getRawParameter("key"));
    }

    @Test
    public void getRawInput_whenExistsInInputs_returnInputValue() {
        when(inputs.get("key")).thenReturn("value");
        assertEquals("value", context.getRawInput("key"));
    }

    @Test
    public void getAsGenericParameter_whenParameterExists_returnsUnmarshalledObject() {
        when(parameters.get("key")).thenReturn("value");
        Any expected = mock(Any.class);
        when(marshaller.unmarshallAsGenericType("value")).thenReturn(expected);
        assertEquals(expected, context.getAsGenericParameter("key"));
    }

    @Test
    public void getAsGenericParameter_whenParameterDoesntExist_returnsNull() {
        when(parameters.get("nonexistent")).thenReturn(null);
        assertNull(context.getAsGenericParameter("nonexistent"));
    }

    @Test
    public void getAsGenericInput_whenInputExists_returnsUnmarshalledObject() {
        when(inputs.get("key")).thenReturn("value");
        Any expected = mock(Any.class);
        when(marshaller.unmarshallAsGenericType("value")).thenReturn(expected);
        assertEquals(expected, context.getAsGenericInput("key"));
    }

    @Test
    public void getAsGenericInput_whenInputDoesntExist_returnsNull() {
        when(inputs.get("nonexistent")).thenReturn(null);
        assertNull(context.getAsGenericInput("nonexistent"));
    }

    @Test
    public void getParameter_whenParameterExists_returnsUnmarshalledObject() {
        when(parameters.get("key")).thenReturn("value");
        String expected = "unmarshalled";
        when(marshaller.unmarshall("value", String.class)).thenReturn(expected);
        assertEquals(expected, context.getParameter("key", String.class));
    }

    @Test
    public void getParameter_whenParameterDoesntExist_returnsNull() {
        when(parameters.get("nonexistent")).thenReturn(null);
        assertNull(context.getParameter("nonexistent", String.class));
    }

    @Test
    public void getInput_whenInputExists_returnsUnmarshalledObject() {
        when(inputs.get("key")).thenReturn("value");
        String expected = "unmarshalled";
        when(marshaller.unmarshall("value", String.class)).thenReturn(expected);
        assertEquals(expected, context.getInput("key", String.class));
    }

    @Test
    public void getInput_whenInputDoesntExist_returnsNull() {
        when(inputs.get("nonexistent")).thenReturn(null);
        assertNull(context.getInput("nonexistent", String.class));
    }

    @Test
    public void getListParameter_whenParameterExists_returnsUnmarshalledCollection() {
        when(parameters.get("key")).thenReturn("value");
        Collection<String> expected = new ArrayList<>();
        expected.add("value1");
        expected.add("value2");
        when(marshaller.unmarshallAsCollection("value", String.class)).thenReturn(expected);
        assertEquals(expected, context.getListParameter("key", String.class));
    }

    @Test
    public void getListParameter_whenParameterDoesntExist_returnsNull() {
        when(parameters.get("nonexistent")).thenReturn(null);
        assertNull(context.getListParameter("nonexistent", String.class));
    }

    @Test
    public void getListInput_whenInputExists_returnsUnmarshalledCollection() {
        when(inputs.get("key")).thenReturn("value");
        Collection<String> expected = new ArrayList<>();
        expected.add("value1");
        expected.add("value2");
        when(marshaller.unmarshallAsCollection("value", String.class)).thenReturn(expected);
        assertEquals(expected, context.getListInput("key", String.class));
    }

    @Test
    public void getListInput_whenInputDoesntExist_returnsNull() {
        when(inputs.get("nonexistent")).thenReturn(null);
        assertNull(context.getListInput("nonexistent", String.class));
    }
}