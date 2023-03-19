package com.maukaim.bulo.shared.server.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KafkaProperties {
    private final List<String> brokers;

    public KafkaProperties(@JsonProperty("brokers")  List<String> brokers) {
        this.brokers = List.copyOf(brokers);
    }

    public List<String> getBrokers() {
        return brokers;
    }
}
