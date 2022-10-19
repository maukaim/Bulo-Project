package com.maukaim.bulo.commons.models;


import java.util.Objects;

public class ContextedStageId {
    private final String stageId;
    private final Integer marker;

    public static ContextedStageId of(String stageId){
        return new ContextedStageId(stageId, null);
    }

    public static ContextedStageId of(String stageId, Integer marker){
        return new ContextedStageId(stageId, marker);
    }
    protected ContextedStageId(String stageId, Integer marker) {
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
        ContextedStageId that = (ContextedStageId) o;
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
