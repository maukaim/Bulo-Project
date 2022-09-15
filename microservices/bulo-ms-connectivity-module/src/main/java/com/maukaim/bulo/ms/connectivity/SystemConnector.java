package com.maukaim.bulo.ms.connectivity;

import com.maukaim.bulo.commons.io.ExternalEvent;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SystemConnector {
    private final RestTemplate restTemplate;

    public SystemConnector(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean sendToConsumers(EventType type, ExternalEvent event){
        List<Consumer> consumers = ConsumersProvider.getConsumers(type);
        boolean result = true;
        for (Consumer consumer : consumers) {
            try{
                this.restTemplate.postForEntity(consumer.toUrl(),event, String.class);
            }catch (Exception e){
                System.out.println(">>> Error while contacting consumer " + consumer);
                System.out.println(e);
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }
}
