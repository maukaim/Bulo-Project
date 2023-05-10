package com.maukaim.bulo.ms.shared.spring.servers.autoconfig.conditions;


import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class RestCallActivatedCondition extends AnyNestedCondition {
    public RestCallActivatedCondition() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }

    @ConditionalOnProperty(prefix = "bulo.communication", value = "mode", havingValue = "rest")
    static class isKafkaCondition {
    }

    @ConditionalOnProperty(prefix = "bulo.communication", value = "mode", havingValue = "compound")
    static class isCompoundCondition {
    }
}
