package com.maukaim.bulo.commons.core.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FlowStageId {
    private final String globalStageId;
    private final Integer marker;

    public static FlowStageId of(String globalStageId){
        return new FlowStageId(globalStageId, null);
    }

    @JsonCreator
    public static FlowStageId of(@JsonProperty("globalStageId") String globalStageId, @JsonProperty("marker")Integer marker){
        return new FlowStageId(globalStageId, marker);
    }
    protected FlowStageId(String globalStageId, Integer marker) {
        this.globalStageId = globalStageId;
        this.marker = marker == null ? 0 : marker;
    }

    public String getGlobalStageId() {
        return globalStageId;
    }

    public Integer getMarker() {
        return marker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStageId that = (FlowStageId) o;
        return globalStageId.equals(that.globalStageId) && Objects.equals(marker, that.marker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalStageId);
    }

    @Override
    public String toString() {
        return marker == null ? globalStageId : globalStageId +
                "[Marker: " +
                marker +
                "]";
    }
}
