package com.maukaim.bulo.commons.models;


import java.util.Objects;

public class ContextualizedStageId {
    private final String stageId;
    private final Integer marker;

    public static ContextualizedStageId of(String stageId){
        return new ContextualizedStageId(stageId, null);
    }

    public static ContextualizedStageId of(String stageId, Integer marker){
        return new ContextualizedStageId(stageId, marker);
    }
    protected ContextualizedStageId(String stageId, Integer marker) {
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
        ContextualizedStageId that = (ContextualizedStageId) o;
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
