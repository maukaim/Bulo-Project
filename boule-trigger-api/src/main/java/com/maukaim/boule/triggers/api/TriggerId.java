package com.maukaim.boule.triggers.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TriggerId {
    private final String flowId;
    private final String stageId;

    @JsonCreator
    public static TriggerId of(@JsonProperty("flowId") String flowId, @JsonProperty("stageId") String stageId){
        return new TriggerId(flowId,stageId);
    }

    public TriggerId(String flowId, String stageId) {
        this.flowId = flowId;
        this.stageId = stageId;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getStageId() {
        return stageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriggerId triggerId = (TriggerId) o;
        return flowId.equals(triggerId.flowId) && stageId.equals(triggerId.stageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowId, stageId);
    }

    @Override
    public String toString() {
        return "TriggerId{" +
                "flowId='" + flowId + '\'' +
                ", stageId='" + stageId + '\'' +
                '}';
    }
}
