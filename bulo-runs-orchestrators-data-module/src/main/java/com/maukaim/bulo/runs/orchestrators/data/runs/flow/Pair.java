package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import java.util.Objects;

public class Pair<F,S>{
    private final F first;
    private final S second;

    public static <FF,SS> Pair<FF,SS> of(FF first, SS second){
        if(first == null || second ==null){
            throw new IllegalArgumentException("Elements of Pair could not be null.");
        }
        return new Pair<>(first, second);
    }

    private Pair(F first, S second){
        this.first = first;
        this.second =second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
