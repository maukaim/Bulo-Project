package com.maukaim.bulo.executors.core.marshalling.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.maukaim.bulo.api.data.types.annotations.BuloDescriptor;
import com.maukaim.bulo.api.data.types.annotations.BuloField;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuloJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {

    @Override
    public JsonCreator.Mode findCreatorAnnotation(MapperConfig<?> config, Annotated a) {
        if (a.hasAnnotation(BuloDescriptor.class)) {
            return JsonCreator.Mode.DEFAULT;
        }
        return super.findCreatorAnnotation(config, a);
    }

    @Override
    public String[] findEnumValues(Class<?> enumType, Enum<?>[] enumValues, String[] names) {
        Map<String, String> overrides = Stream.of(enumType.getDeclaredFields())
                .filter(it -> !it.isEnumConstant())
                .filter(it -> it.getAnnotation(BuloField.class) != null)
                .collect(Collectors.toMap(
                        Field::getName,
                        it -> it.getAnnotation(BuloField.class).value()
                ));

        if (overrides.isEmpty()) {
            return super.findEnumValues(enumType, enumValues, names);
        }

        for (int i = 0; i < enumValues.length; ++i) {
            names[i] = overrides.getOrDefault(enumValues[i].name(), names[i]);
        }
        return names;
    }

    @Override
    public PropertyName findNameForSerialization(Annotated a) {
        BuloField fieldName = a.getAnnotation(BuloField.class);
        if (fieldName != null) {
            return PropertyName.construct(fieldName.value());
        }

        return super.findNameForSerialization(a);
    }

    @Override
    public PropertyName findNameForDeserialization(Annotated a) {
        BuloField fieldName = a.getAnnotation(BuloField.class);
        if (fieldName != null) {
            return PropertyName.construct(fieldName.value());
        }

        return super.findNameForDeserialization(a);
    }
}
