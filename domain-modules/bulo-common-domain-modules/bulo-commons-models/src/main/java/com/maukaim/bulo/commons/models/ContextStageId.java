package com.maukaim.bulo.commons.models;


import java.util.Objects;

public class ContextStageId {
    private final String stageId;
    private final Integer marker;

    public static ContextStageId of(String stageId){
        return new ContextStageId(stageId, null);
    }

    public static ContextStageId of(String stageId, Integer marker){
        return new ContextStageId(stageId, marker);
    }
    protected ContextStageId(String stageId, Integer marker) {
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
        ContextStageId that = (ContextStageId) o;
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
