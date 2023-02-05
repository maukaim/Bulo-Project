package com.maukaim.bulo.standalone.app;

import com.maukaim.bulo.app.shared.spring.servers.StandardSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.maukaim.bulo.app.shared.system.communication.api.ServiceName.STANDALONE;

@SpringBootApplication
public class BuloStandaloneApplication extends StandardSpringApplication {

    public static void main(String[] args){
        launchSpringApp(STANDALONE, BuloStandaloneApplication.class, args);
    }
}
