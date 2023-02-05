package com.maukaim.bulo.app.shared.servers;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.servers.model.WebServerProperties;
import org.junit.jupiter.api.Test;

public class ServerUtilsTest {
    @Test
    void getWebServerProperties() {
        WebServerProperties webServerProperties = ServerUtils.getWebServerProperties(ServiceName.STANDALONE);
        System.out.println(webServerProperties);
    }
}
