package com.maukaim.bulo.commons.core.utils;

import java.time.Instant;

public class TimeHelper {

    public static boolean isBefore(Instant ref, Instant other){
        return ref != null && (other == null ||  ref.isBefore(other));
    }

    public static boolean isAfter(Instant ref, Instant other){
        return ref != null && (other == null ||  ref.isAfter(other));
    }
}
