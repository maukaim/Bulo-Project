package com.maukaim.bulo.shared.server.core;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.shared.server.core.model.WebServerProperties;
import org.junit.jupiter.api.Test;

public class ServerUtilsTest {
    @Test
    void getWebServerProperties() {
        WebServerProperties webServerProperties = ServerUtils.getWebServerProperties(ServiceName.STANDALONE);
        System.out.println(webServerProperties);
    }
}
