package com.maukaim.bulo.triggers.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

public class TriggerId {
    private final String flowId;
    private final Set<String> stageIds;

    @JsonCreator
    public static TriggerId of(@JsonProperty("flowId") String flowId, @JsonProperty("stageIds") Set<String> stageIds){
        return new TriggerId(flowId,stageIds);
    }

    public TriggerId(String flowId, Set<String> stageIds) {
        this.flowId = flowId;
        this.stageIds = stageIds;
    }

    public String getFlowId() {
        return flowId;
    }

    public Set<String> getStageIds() {
        return stageIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriggerId triggerId = (TriggerId) o;
        return flowId.equals(triggerId.flowId) && Objects.equals(stageIds, triggerId.stageIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowId, stageIds);
    }

    @Override
    public String toString() {
        return "TriggerId{" +
                "flowId='" + flowId + '\'' +
                ", stageIds='" + stageIds + '\'' +
                '}';
    }
}
