package com.maukaim.bulo.ms.shared.system.communication.kafka;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;

public class KafkaUtil {
    public static String toGroupId(ServiceName serviceName){
        return serviceName.toString().toLowerCase();
    }
}