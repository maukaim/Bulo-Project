package com.maukaim.bulo.ms.shared.spring.servers.autoconfig.conditions;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class KafkaActivatedCondition extends AnyNestedCondition {
    public KafkaActivatedCondition() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }

    @ConditionalOnProperty(prefix = "bulo.communication", value = "mode", havingValue = "kafka")
    static class isKafkaCondition {
    }

    @ConditionalOnProperty(prefix = "bulo.communication", value = "mode", havingValue = "compound")
    static class isCompoundCondition {
    }
}
