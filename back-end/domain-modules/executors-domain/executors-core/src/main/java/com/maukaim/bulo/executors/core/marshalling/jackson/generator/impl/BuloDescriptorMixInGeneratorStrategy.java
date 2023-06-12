package com.maukaim.bulo.executors.core.marshalling.jackson.generator.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.api.data.types.annotations.BuloDescriptor;
import com.maukaim.bulo.api.data.types.annotations.BuloField;
import com.maukaim.bulo.executors.core.marshalling.jackson.generator.MixInGeneratorStrategy;
import com.maukaim.bulo.runners.api.ExecutionCancelledException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.modifier.MethodManifestation;
import net.bytebuddy.description.modifier.TypeManifestation;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodCall;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

//TODO: Cover with Unit Tests
public class BuloDescriptorMixInGeneratorStrategy extends MixInGeneratorStrategy {
    private static final String BULO_PREFIX_CLASS_NAME = "Bulo";
    private static final String MIXIN_SUFFIX_CLASS_NAME = "MixIn";

    @Override
    protected Map<Class<?>, Class<?>> generateMixIns(String value, Class<?> clazz) {
        return generateMixIns(clazz);
    }

    private Map<Class<?>, Class<?>> generateMixIns(Class<?> clazz) {
        Map<Class<?>, Class<?>> result = new HashMap<>();

        for (Constructor<?> constructor : clazz.getConstructors()) {
            BuloDescriptor buloAnnotation = constructor.getAnnotation(BuloDescriptor.class);

            if (buloAnnotation != null) {
                Class<?> mixInClass = createMixIn(constructor, clazz);
                result.put(clazz, mixInClass);
                Map<Class<?>, Class<?>> parametersMixIn = generateMixIns(constructor.getParameters());
                result.putAll(parametersMixIn);
                return result;
            }
        }
        return result;
    }

    private Map<Class<?>, Class<?>> generateMixIns(Parameter[] parameters) {
        Map<Class<?>, Class<?>> result = new HashMap<>();
        for (Parameter parameter : parameters) {
            Class<?> clazz = parameter.getType();
            Map<Class<?>, Class<?>> mixInsRequiredForClass = generateMixIns(clazz);
            result.putAll(mixInsRequiredForClass);
        }
        return result;
    }

    private Class<?> createMixIn(Constructor<?> constructor, Class<?> clazz) {
        DynamicType.Builder<Object> mixInBuilder = new ByteBuddy()
                .subclass(Object.class)
                .modifiers(Visibility.PUBLIC, TypeManifestation.ABSTRACT)
                .name(getMixInClassName(clazz.getSimpleName()))
                .annotateType(AnnotationDescription.Builder.ofType(JsonIgnoreProperties.class).define("ignoreUnknown", true).build());

        var builderWithConstructor = appendConstructor(mixInBuilder, constructor);
        var builderWithGetters = appendGetters(builderWithConstructor, constructor);

        return builderWithGetters.make().load(getClass().getClassLoader()).getLoaded();
    }

    private DynamicType.Builder.MethodDefinition<Object> appendGetters(DynamicType.Builder.MethodDefinition<Object> withConstructor, Constructor<?> constructor) {
        DynamicType.Builder.MethodDefinition<Object> result = withConstructor;
        for (Parameter parameter : constructor.getParameters()) {
            BuloField buloFieldAnnotation = parameter.getAnnotation(BuloField.class);
            if (buloFieldAnnotation == null) {
                throw ExecutionCancelledException.jsonDeserialization(String.format(
                        "Constructor of class %s has constructor with BuloDescriptor but the parameter %s does not have BuloField annotation.",
                        constructor.getDeclaringClass(),
                        parameter.isNamePresent() ? parameter.getName() : parameter.getParameterizedType()));
            }
            String parameterName = buloFieldAnnotation.value();
            result = result.defineMethod(getGetterMethodName(parameterName, parameter.getType()), parameter.getType(), Visibility.PUBLIC, MethodManifestation.ABSTRACT)
                    .withoutCode()
                    .annotateMethod(AnnotationDescription.Builder.ofType(JsonProperty.class).define("value", parameterName).build());
        }
        return result;
    }

    private DynamicType.Builder.MethodDefinition<Object> appendConstructor(DynamicType.Builder<Object> mixInBuilder, Constructor<?> constructor) {
        DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial<Object> constructorBuilder = mixInBuilder.defineConstructor(Modifier.PUBLIC);
        DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable<Object> parameterizedConstructorBuilder = null;
        for (Parameter parameter : constructor.getParameters()) {
            BuloField buloFieldAnnotation = parameter.getAnnotation(BuloField.class);
            if (buloFieldAnnotation == null) {
                throw ExecutionCancelledException.jsonDeserialization(String.format(
                        "Constructor of class %s has constructor with BuloDescriptor but the parameter %s does not have BuloField annotation.",
                        constructor.getDeclaringClass(),
                        parameter.isNamePresent() ? parameter.getName() : parameter.getParameterizedType()));
            }
            if (parameterizedConstructorBuilder == null) {
                parameterizedConstructorBuilder = constructorBuilder.withParameter(parameter.getType())
                        .annotateParameter(AnnotationDescription.Builder.ofType(JsonProperty.class).define("value", buloFieldAnnotation.value()).build());
            } else {
                parameterizedConstructorBuilder = parameterizedConstructorBuilder.withParameter(parameter.getType())
                        .annotateParameter(AnnotationDescription.Builder.ofType(JsonProperty.class).define("value", buloFieldAnnotation.value()).build());
            }
        }

        return (parameterizedConstructorBuilder == null ? constructorBuilder : parameterizedConstructorBuilder)
                .intercept(MethodCall.invoke(Object.class.getConstructors()[0]).onSuper())
                .annotateMethod(AnnotationDescription.Builder.ofType(JsonCreator.class).build());
    }

    private String getGetterMethodName(String fieldName, Class<?> type) {
        if (type.isAssignableFrom(Boolean.class)) {
            return fieldName.startsWith("is") || fieldName.startsWith("has") ?
                    fieldName : "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private String getMixInClassName(String className) {
        return BULO_PREFIX_CLASS_NAME + className + MIXIN_SUFFIX_CLASS_NAME;
    }

}
