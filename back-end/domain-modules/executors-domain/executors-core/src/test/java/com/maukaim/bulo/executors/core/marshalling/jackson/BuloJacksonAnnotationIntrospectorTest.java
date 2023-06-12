package com.maukaim.bulo.executors.core.marshalling.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.maukaim.bulo.api.data.types.annotations.BuloDescriptor;
import com.maukaim.bulo.api.data.types.annotations.BuloField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuloJacksonAnnotationIntrospectorTest {

    @Test
    public void findCreatorAnnotation_whenBuloDescriptorAnnotationExists_returnDefaultMode() {
        MapperConfig<?> config = mock(MapperConfig.class);
        Annotated annotated = mock(Annotated.class);
        when(annotated.hasAnnotation(BuloDescriptor.class)).thenReturn(true);

        BuloJacksonAnnotationIntrospector inspector = new BuloJacksonAnnotationIntrospector();
        assertEquals(JsonCreator.Mode.DEFAULT, inspector.findCreatorAnnotation(config, annotated));
    }

    @Test
    public void findCreatorAnnotation_whenBuloDescriptorAnnotationNotExists_returnSuperResult() {
        MapperConfig<?> config = mock(MapperConfig.class);
        Annotated annotated = mock(Annotated.class);
        when(annotated.hasAnnotation(BuloDescriptor.class)).thenReturn(false);

        BuloJacksonAnnotationIntrospector inspector = spy(new BuloJacksonAnnotationIntrospector());
        inspector.findCreatorAnnotation(config, annotated);
        verify(inspector).findCreatorAnnotation(config, annotated);
    }

    // More detailed tests are required for findEnumValues method, you might need to write several tests for it
    // to cover all edge cases.

    @Test
    public void findNameForSerialization_whenBuloFieldAnnotationExists_returnPropertyName() {
        Annotated annotated = mock(Annotated.class);
        BuloField buloField = mock(BuloField.class);
        when(annotated.getAnnotation(BuloField.class)).thenReturn(buloField);
        when(buloField.value()).thenReturn("value");

        BuloJacksonAnnotationIntrospector inspector = new BuloJacksonAnnotationIntrospector();
        assertEquals(PropertyName.construct("value"), inspector.findNameForSerialization(annotated));
    }

    @Test
    public void findNameForSerialization_whenBuloFieldAnnotationNotExists_returnSuperResult() {
        Annotated annotated = mock(Annotated.class);
        when(annotated.getAnnotation(BuloField.class)).thenReturn(null);

        BuloJacksonAnnotationIntrospector inspector = spy(new BuloJacksonAnnotationIntrospector());
        inspector.findNameForSerialization(annotated);
        verify(inspector).findNameForSerialization(annotated);
    }

    @Test
    public void findNameForDeserialization_whenBuloFieldAnnotationExists_returnPropertyName() {
        Annotated annotated = mock(Annotated.class);
        BuloField buloField = mock(BuloField.class);
        when(annotated.getAnnotation(BuloField.class)).thenReturn(buloField);
        when(buloField.value()).thenReturn("value");

        BuloJacksonAnnotationIntrospector inspector = new BuloJacksonAnnotationIntrospector();
        assertEquals(PropertyName.construct("value"), inspector.findNameForDeserialization(annotated));
    }

    @Test
    public void findNameForDeserialization_whenBuloFieldAnnotationNotExists_returnSuperResult() {
        Annotated annotated = mock(Annotated.class);
        when(annotated.getAnnotation(BuloField.class)).thenReturn(null);

        BuloJacksonAnnotationIntrospector inspector = spy(new BuloJacksonAnnotationIntrospector());
        inspector.findNameForDeserialization(annotated);
        verify(inspector).findNameForDeserialization(annotated);
    }

    /**
     *
     */
    @Test
    public void findEnumValues_whenBuloFieldAnnotationExists_returnOverriddenName() {
        BuloJacksonAnnotationIntrospector inspector = new BuloJacksonAnnotationIntrospector();
        String[] names = new String[] {"VALUE1", "VALUE2"};

        String[] result = inspector.findEnumValues(EnumWithBuloField.class, EnumWithBuloField.values(), names);

        assertEquals("CustomValue1", result[0]); // For VALUE1, we should get the overridden value
        assertEquals("VALUE2", result[1]); // For VALUE2, we should get the original value since there's no annotation
    }

    @Test
    public void findEnumValues_whenBuloFieldAnnotationNotExists_returnOriginalNames() {
        BuloJacksonAnnotationIntrospector inspector = new BuloJacksonAnnotationIntrospector();
        String[] names = new String[] {"VALUE1", "VALUE2"};

        String[] result = inspector.findEnumValues(EnumWithoutBuloField.class, EnumWithoutBuloField.values(), names);

        assertEquals("VALUE1", result[0]); // We should get the original values
        assertEquals("VALUE2", result[1]);
    }


    public enum EnumWithBuloField {
        @BuloField("CustomValue1")
        VALUE1,
        VALUE2
    }

    public enum EnumWithoutBuloField {
        VALUE1,
        VALUE2
    }
}