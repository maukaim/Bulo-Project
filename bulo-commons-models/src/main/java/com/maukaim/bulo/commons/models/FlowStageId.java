package com.maukaim.bulo.commons.models;


import java.util.Objects;

public class FlowStageId {
    private final String stageId;
    private final Integer marker;

    public static FlowStageId of(String stageId){
        return new FlowStageId(stageId, null);
    }

    public static FlowStageId of(String stageId, Integer marker){
        return new FlowStageId(stageId, marker);
    }
    protected FlowStageId(String stageId, Integer marker) {
        this.stageId = stageId;
        this.marker = marker == null ? 0 : marker;
    }

    public String getStageId() {
        return stageId;
    }

    public Integer getMarker() {
        return marker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStageId that = (FlowStageId) o;
        return stageId.equals(that.stageId) && Objects.equals(marker, that.marker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stageId);
    }

    @Override
    public String toString() {
        return marker == null ? stageId : stageId +
                "[Marker: " +
                marker +
                "]";
    }
}
