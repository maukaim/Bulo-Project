package com.maukaim.bulo.api.data.types;

public abstract class Any<T> {
    private T value;

    public Any(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "value=" + value +
                '}';
    }
}